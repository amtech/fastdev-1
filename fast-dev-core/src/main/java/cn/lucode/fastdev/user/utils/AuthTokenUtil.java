package cn.lucode.fastdev.user.utils;

import cn.lucode.fastdev.common.ReturnCodeModel;
import cn.lucode.fastdev.redis.OcsParam;
import cn.lucode.fastdev.user.model.AuthInfoModel;
import cn.lucode.fastdev.util.LogUtil;
import cn.lucode.fastdev.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * @author yunfeng.lu
 * @create 2017/12/4.
 */
@Component
@Scope("singleton")
public class AuthTokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenUtil.class);
    private String KNOWN = "known";
    @Autowired
    private UserCacheUtil userCacheUtil;

    public String createAuthToken(AuthInfoModel authInfoModel) {
        if (StringUtil.isEmpty(authInfoModel.getDeviceId())) {
            authInfoModel.setDeviceId(KNOWN);
        }
        if (StringUtil.isEmpty(authInfoModel.getIdentity())) {
            authInfoModel.setIdentity(KNOWN);
        }
        if (StringUtil.isEmpty(authInfoModel.getIp())) {
            authInfoModel.setIp(KNOWN);
        }
        if (StringUtil.isEmpty(authInfoModel.getOsType())) {
            authInfoModel.setOsType(KNOWN);
        }
        if (StringUtil.isEmpty(authInfoModel.getSourceId())) {
            authInfoModel.setSourceId(KNOWN);
        }

        StringBuilder sb = new StringBuilder();
        // 四位随机盐
        sb.append(randomStr(4));
        sb.append("__");
        // 第一位 用户 id
        sb.append(authInfoModel.getUserId());
        sb.append("__");
        // 第二位 设备 id
        sb.append(authInfoModel.getDeviceId());
        sb.append("__");
        // 第三位  系统类型
        sb.append(authInfoModel.getOsType());
        sb.append("__");
        // 第四位  源来 id
        sb.append(authInfoModel.getSourceId());
        sb.append("__");
        // 第五位  用户 ip
        sb.append(authInfoModel.getIp());
        sb.append("__");
        // 第六位 身份
        sb.append(authInfoModel.getIdentity());
        sb.append("__");
        long now = new Date().getTime();
        // 第七位 时间点
        sb.append(now);
        try {
            byte[] encodeBase64 = Base64.getEncoder()
                    .encode(AESUtil.encrypt(AESUtil.AUTHTOKEN_AES_KEY, sb.toString()).getBytes());
            String result = new String(encodeBase64);
            // 在这里 保存redis
            userCacheUtil.saveAuthTokenCache(authInfoModel);
            return result;
        } catch (Exception e) {
            LogUtil.error(logger, e, "创建authToken出错");
            throw new UserException(ReturnCodeModel.UNAUTHORIZED);
        }
    }

    /**
     * 通过AuthToken获取用户信息，注意第一位是四位的随机盐,第二位是用户ID，第三位是设备ID
     * 解析后会检查authToken的有效性
     *
     * @param authToken
     * @return
     */
    public AuthInfoModel parseAuthToken(String authToken) throws UserException {
        // 解析出 authToken 包含的用户信息
        AuthInfoModel info = parseAuthTokenWithoutCheck(authToken);
        //检查authToken的有效性
        checkAuthToken(info);
        return info;
    }

    /**
     * 通过AuthToken获取用户信息
     *
     * @param authToken
     * @return
     */
    public AuthInfoModel parseAuthTokenWithoutCheck(String authToken) throws UserException {
        if (StringUtils.isEmpty(authToken)) {
            throw new UserException(ReturnCodeModel.UNAUTHORIZED);
        }
        AuthInfoModel info = new AuthInfoModel();
        try {
            String str = new String(Base64.getDecoder().decode(authToken));
            String str2 = AESUtil.decrypt(AESUtil.AUTHTOKEN_AES_KEY, str);
            String[] arr = str2.split("__");
            if (arr.length != 8) {
                throw new UserException(ReturnCodeModel.UNAUTHORIZED);
            }
            List<String> resultList = Arrays.asList(arr);
            info.setUserId(resultList.get(1));
            info.setDeviceId(resultList.get(2));
            info.setOsType(resultList.get(3));
            info.setSourceId(resultList.get(4));
            info.setIp(resultList.get(5));
            info.setIdentity(resultList.get(6));
            info.setTokenTime(Long.parseLong(resultList.get(7)));
        } catch (Exception e) {
            LogUtil.error(logger, e, "解析authToken出错");
            throw new UserException(ReturnCodeModel.UNAUTHORIZED);
        }
        return info;
    }

    /**
     * 从缓存中检查当前用户的登录状态 检查包括  终端 ip 两个点
     * 超过时间则抛出AuthToken失效异常
     *
     * @param info
     * @return
     */
    private void checkAuthToken(AuthInfoModel info) {
        AuthInfoModel redisInfo = userCacheUtil.getAuthTokenCache(info.getUserId());
        //如果缓存中不存在，则认为它失效
        if (redisInfo == null) {
            throw new UserException(ReturnCodeModel.AUTHTOKEN_INVALID);
        }
        //判断终端登录
        if (!redisInfo.getDeviceId().equals(info.getDeviceId())) {
            throw new UserException(ReturnCodeModel.DEVICE_INVALID);
        }
        //判断 ip是否一致   防止代理 ip 切换方式爬取资源
        if (!redisInfo.getIp().equals(info.getIp())) {
            throw new UserException(ReturnCodeModel.DEVICE_INVALID);
        }
        long nowTimeDiff = new Date().getTime() - redisInfo.getTokenTime();
        // 超过半小时就刷新缓存时间
        if (nowTimeDiff > (OcsParam.OCSPARAM_MIN * 30)) {
            userCacheUtil.saveAuthTokenCache(info);
        }
    }

    private String randomStr(int len) {
        StringBuilder result = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < len; i++) {
            int index = (int) (Math.random() * 26);
            result.append(chars.charAt(index));
        }
        return result.toString();
    }


    public static void main(String[] args) {
        AuthTokenUtil authTokenUtil = new AuthTokenUtil();
        AuthInfoModel authInfoModel = new AuthInfoModel();
        authInfoModel.setUserId("dadasds");

        String a = authTokenUtil.createAuthToken(authInfoModel);
        System.out.println(a);
        System.out.println(authTokenUtil.parseAuthTokenWithoutCheck(a));

    }
}
