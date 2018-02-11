package cn.lucode.fastdev.user.utils;

import cn.lucode.fastdev.common.ReturnCodeModel;
import cn.lucode.fastdev.redis.OcsParam;
import cn.lucode.fastdev.redis.service.IRedisOperation;
import cn.lucode.fastdev.user.model.AuthInfoModel;
import cn.lucode.fastdev.user.model.RegisterReq;
import cn.lucode.fastdev.util.JsonUtil;
import cn.lucode.fastdev.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 用户信息的缓存进一步封装，统一失效时间管理
 *
 * @author yunfeng.lu
 * @create 2017/12/4.
 */
@Component
@Scope("singleton")
public class UserCacheUtil {
    @Autowired
    private IRedisOperation redisOperation;
    private static String HASHKEY_AUTHINFO = "authInfo";

    /**
     * 保存登录状态缓存,1小时有效
     */
    public void saveAuthTokenCache(AuthInfoModel authInfoModel) {
        authInfoModel.setTokenTime(new Date().getTime());

        redisOperation.setHashValue(authInfoModel.getUserId(),
                HASHKEY_AUTHINFO, JsonUtil.obj2JsonStr(authInfoModel));
        // 设置超时 时间为7天
        redisOperation.setTimeout(authInfoModel.getUserId(), OcsParam.OCSPARAM_DAY * 7);

    }

    /**
     * 获取登录状态缓存
     *
     * @param userId
     */
    public AuthInfoModel getAuthTokenCache(String userId) {
        String redisVal = redisOperation.getHashValue(userId, HASHKEY_AUTHINFO);
        //如果缓存中不存在，则认为它失效
        if (StringUtil.isEmpty(redisVal)) {
            return null;
        }
        return JsonUtil.json2Bean(redisVal, AuthInfoModel.class);
    }

    /**
     * 保存临时注册信息
     */
    public void saveTempRegisterInfo(RegisterReq registerReq) {
        //验证码  10分钟有效期
        redisOperation.setValue(registerReq.getVerifCode(), JsonUtil.obj2JsonStr(registerReq), OcsParam.OCSPARAM_MIN * 10, TimeUnit.MILLISECONDS);
    }


    public void getTempRegisterInfo(RegisterReq registerReq) {
        String redisVal = redisOperation.getValue(registerReq.getVerifCode());
        //如果缓存中不存在，则认为它 验证失败
        if (StringUtil.isEmpty(redisVal)) {
            throw new UserException(ReturnCodeModel.VERIFICODEERROR);
        }
        RegisterReq cache = JsonUtil.json2Bean(redisVal, RegisterReq.class);
        // 两个对象之间比较
        if (!registerReq.getEmail().equals(cache.getEmail()) ||
                !registerReq.getLoginName().equals(cache.getLoginName()) ||
                !registerReq.getPassword().equals(cache.getPassword())) {
            throw new UserException(ReturnCodeModel.VERIFICODEERROR);
        }

    }


    /**
     * 删除  登录态
     *
     * @param userId
     */
    public void delAuthTokenCache(String userId) {
        redisOperation.removeHashEntry(userId, HASHKEY_AUTHINFO);
    }


}
