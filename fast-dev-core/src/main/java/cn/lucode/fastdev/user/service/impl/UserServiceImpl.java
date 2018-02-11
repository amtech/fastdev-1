package cn.lucode.fastdev.user.service.impl;

import cn.hutool.core.codec.Base32;
import cn.hutool.core.util.RandomUtil;
import cn.lucode.fastdev.annotation.CommonAppCode;
import cn.lucode.fastdev.annotation.LogAuto;
import cn.lucode.fastdev.common.ReturnCodeModel;
import cn.lucode.fastdev.enums.TemplateType;
import cn.lucode.fastdev.redis.OcsParam;
import cn.lucode.fastdev.redis.service.IRedisOperation;
import cn.lucode.fastdev.user.dal.dao.UserMapper;
import cn.lucode.fastdev.user.dal.pojo.User;
import cn.lucode.fastdev.user.model.*;
import cn.lucode.fastdev.user.service.UserService;
import cn.lucode.fastdev.user.utils.AuthTokenUtil;
import cn.lucode.fastdev.user.utils.PasswordUtil;
import cn.lucode.fastdev.user.utils.UserCacheUtil;
import cn.lucode.fastdev.user.utils.UserException;
import cn.lucode.fastdev.util.JsonUtil;
import cn.lucode.fastdev.util.StringUtil;
import cn.lucode.fastdev.util.UUIDGenerator;
import cn.lucode.fastdev.verify.EmailModel;
import cn.lucode.fastdev.verify.service.QQEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author yunfeng.lu
 * @create 2017/12/4.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordUtil passwordUtil;

    @Autowired
    private AuthTokenUtil authTokenUtil;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private UserCacheUtil userCacheUtil;

    @Autowired
    private QQEmailService qqEmailService;

    @Autowired
    private IRedisOperation redisOperation;


    public Object quickLogin() {
        return null;
    }

    @Override
    @LogAuto(CommonAppCode.FastDevApp)
    public LoginRes login(LoginReq loginReq) {

        if (StringUtil.isNil(loginReq.getLoginName()) ||
                StringUtil.isNil(loginReq.getPassword())) {

            throw new UserException(ReturnCodeModel.PARAMETER_IS_MISSING);

        }
        //根据用户名查询 用户信息
        User user = userMapper.selectByLoginName(loginReq.getLoginName());

        if (user == null) {

            throw new UserException(ReturnCodeModel.NOT_REGISTER);

        }

        if (StringUtil.isNil(user.getPassword())) {

            throw new UserException(ReturnCodeModel.NOT_GENERATED_PASSWORD);

        }

        // 验证密码
        boolean checkFlg = passwordUtil.checkLogin(loginReq.getPassword(), user.getPassword());

        if (!checkFlg) {
            // TODO 记录登陆失败日志 待完成

            // 登陆 失败 抛出 异常
            throw new UserException(ReturnCodeModel.PASSWORDERROR);

        }

        String uid = user.getUserId();

        // TODO 记录登陆成功日志 待完成

        // 用户的个人信息封装成 model
        UserInfoModel userInfoModel = UserInfoModel.pojo2Model(user);

        // 得到 authToken
        AuthInfoModel authInfoModel = new AuthInfoModel();

        authInfoModel.setUserId(uid);

        authInfoModel.setOsType("mac");

        String authToken = authTokenUtil.createAuthToken(authInfoModel);

        // 封装返回值
        LoginRes loginRes = new LoginRes();

        loginRes.setAuthToken(authToken);

        loginRes.setUserInfo(userInfoModel);

        return loginRes;
    }


    /**
     * 注册第一步 得到用户信息并且发送验证码
     *
     * @param registerReq
     * @return
     */
    @Override
    @LogAuto(CommonAppCode.FastDevApp)
    public String registerStep1(RegisterReq registerReq) {

        if (StringUtil.isNil(registerReq.getLoginName()) ||
                StringUtil.isNil(registerReq.getPassword()) ||
                StringUtil.isNil(registerReq.getEmail())) {
            throw new UserException(ReturnCodeModel.PARAMETER_IS_MISSING);
        }
        //幂等校验，防止重复发送  一分钟之内
        if (redisOperation.getValue(JsonUtil.obj2JsonStr(registerReq)) != null) {
            return StringUtil.email2Url(registerReq.getEmail());
        } else {
            redisOperation.setValue(JsonUtil.obj2JsonStr(registerReq), "1", OcsParam.OCSPARAM_MIN, TimeUnit.MILLISECONDS);
        }
        //根据用户名查询 用户信息
        User user = userMapper.selectByLoginName(registerReq.getLoginName());
        User user2 = userMapper.selectByEmail(registerReq.getEmail());

        if (user != null || user2 != null) {
            throw new UserException(ReturnCodeModel.REGISTERED);
        }

        String emailUrl = StringUtil.email2Url(registerReq.getEmail());
        if (emailUrl == null) {
            // 非法邮箱异常
            throw new UserException(ReturnCodeModel.EMAILERROR);
        } else {
            // 返回邮箱的登陆地址，同时往用户的邮箱发送一份注册邮件,同时将用户的注册信息保存起来
            EmailModel emailModel = new EmailModel();
            emailModel.setTemplateType(TemplateType.REGISTER);
            emailModel.setReceiver(registerReq.getEmail());
            // 随机生成 验证码
            String verifCode = UUIDGenerator.generate();
            emailModel.setVerifCode(verifCode);
            try {
                qqEmailService.sendAttachmentsMail(emailModel);
            } catch (Exception e) {
                // 邮箱发送失败异常
                throw new UserException(ReturnCodeModel.REGISTERED);
            }

            /**
             * 将 验证码 保存在 redis
             * 保存形式是
             * key=id  value=注册信息
             * 失效时间10分钟
             */
            registerReq.setVerifCode(verifCode);
            userCacheUtil.saveTempRegisterInfo(registerReq);
            // 返回当前输入邮箱的 登录页
            return emailUrl;
        }
    }

    @Override
    @LogAuto(CommonAppCode.FastDevApp)
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void registerStep2(RegisterReq registerReq) {
        if (StringUtil.isNil(registerReq.getVerifCode())) {
            throw new UserException(ReturnCodeModel.PARAMETER_IS_MISSING);
        }
        // 校验 验证码
        userCacheUtil.getTempRegisterInfo(registerReq);
        // 再次进行 验证 是否已存在
        //根据用户名查询 用户信息
        User user = userMapper.selectByLoginName(registerReq.getLoginName());
        User user2 = userMapper.selectByEmail(registerReq.getEmail());

        if (user != null || user2 != null) {
            throw new UserException(ReturnCodeModel.REGISTERED);
        }

        // 开始进入注册流程
        User newUser = new User();
        // 拼装参数
        newUser.setUserId(UUIDGenerator.generate());
        newUser.setLoginName(registerReq.getLoginName());
        //密码加密
        newUser.setPassword(passwordUtil.createPassword(registerReq.getPassword()));
        newUser.setNickname(registerReq.getLoginName());
        //这里随机一个默认头像
        newUser.setAvatar("https://tuimeizi.cn/random?w=100&h=100&s=0&o=" + RandomUtil.randomInt(0, 100));
        newUser.setGender("m");
        newUser.setUserEmail(registerReq.getEmail());
        newUser.setUserEnableStatus(1);

        Date date = new Date();
        newUser.setGmtCreate(date);
        newUser.setGmtModify(date);

        userMapper.insertSelective(newUser);
//
//        // 注册成功的话在这里返回用户的信息
//        // 用户的个人信息封装成 model
//        UserInfoModel userInfoModel = UserInfoModel.pojo2Model(newUser);
//
//        // 得到 authToken
//        AuthInfoModel authInfoModel = new AuthInfoModel();
//
//        authInfoModel.setUserId(newUser.getUserId());
//
//        authInfoModel.setOsType("mac");
//
//        String authToken = authTokenUtil.createAuthToken(authInfoModel);
//
//        // 封装返回值
//        RegisterRes res = new RegisterRes();
//
//        res.setAuthToken(authToken);
//
//        res.setUserInfo(userInfoModel);
//
//        return res;
    }


    /**
     * 得到用户信息  并登陆状态检查
     *
     * @param authToken
     * @return
     */
    @Override
    @LogAuto(CommonAppCode.FastDevApp)
    public UserInfoModel getUserInfo(String authToken) {
        AuthInfoModel authInfoModel = authTokenUtil.parseAuthToken(authToken);
        // 登陆失效
        if (authInfoModel == null) {
            throw new UserException(ReturnCodeModel.AUTHTOKEN_INVALID);
        }

        User user = userMapper.selectByPrimaryKey(authInfoModel.getUserId());

        if (user == null) {
            throw new UserException(ReturnCodeModel.NO_DATE);
        }
        return UserInfoModel.pojo2Model(user);
    }


    @Override
    @LogAuto(CommonAppCode.FastDevApp)
    public void layout(String authToken) {
        AuthInfoModel authInfoModel = authTokenUtil.parseAuthToken(authToken);
        // 登陆失效
        if (authInfoModel == null) {
            throw new UserException(ReturnCodeModel.AUTHTOKEN_INVALID);
        }

        userCacheUtil.delAuthTokenCache(authInfoModel.getUserId());

    }


    /**
     * 检查登陆状态 返回 用户信息
     * 入参之后会加上 身份 , ip ,设备号的校验 ，防止通过得到 authToken 进行爬虫，或者其他活动
     *
     * @param authToken
     */
    public void checkLoginState(String authToken) {
        authTokenUtil.parseAuthToken(authToken);
    }


}
