//package cn.lucode.fastdev.user.utils;
//
//import cn.lucode.fastdev.common.ReturnCodeModel;
//import cn.lucode.fastdev.user.model.AuthInfoModel;
//import cn.lucode.fastdev.util.LogUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.Date;
//import java.util.List;
//
///**
// * @author yunfeng.lu
// * @create 2017/12/4.
// */
//@Component
//@Scope("singleton")
//public class AuthTokenUtil {
//    private static final Logger logger = LoggerFactory.getLogger(AuthTokenUtil.class);
//
//    @Autowired
//    private UserCacheUtil userCacheUtil;
//
//    public String createAuthToken(String userId, String deviceId,String osType) {
//        StringBuilder sb = new StringBuilder();
//        // 四位随机盐
//        sb.append(randomStr(4));
//        sb.append("__");
//        sb.append(userId);
//        sb.append("__");
//        sb.append(deviceId);
//        sb.append("__");
//        sb.append(osType);
//        sb.append("__");
//        long now = new Date().getTime();
//        sb.append(now);
//        try {
//            byte[] encodeBase64 = Base64.getEncoder()
//                    .encode(AESUtil.encrypt(AESUtil.AUTHTOKEN_AES_KEY, sb.toString()).getBytes());
//            String result = new String(encodeBase64);
//            return result.replaceAll("=", "");
//        } catch (Exception e) {
//            LogUtil.error(logger, e, "创建authToken出错");
//            throw new UserException(ReturnCodeModel.UNAUTHORIZED);
//        }
//    }
//
//    /**
//     * 通过AuthToken获取用户信息，注意第一位是四位的随机盐,第二位是用户ID，第三位是设备ID
//     * 解析后会检查authToken的有效性
//     *
//     * @param authToken
//     * @return
//     */
//    public AuthInfoModel parseAuthToken(String authToken) throws UserException {
//        AuthInfoModel info = parseAuthTokenWithoutCheck(authToken);
//        //检查authToken的有效性
//        checkAuthToken(info);
//        return info;
//    }
//
//    /**
//     * 通过AuthToken获取用户信息，注意第一位是四位的随机盐,第二位是用户ID，第三位是设备ID,第四位是设备类型
//     *
//     * @param authToken
//     * @return
//     */
//    public AuthInfoModel parseAuthTokenWithoutCheck(String authToken) throws UserException {
//        if (StringUtils.isEmpty(authToken)) {
//            throw new UserException(ReturnCodeModel.UNAUTHORIZED);
//        }
//        AuthInfoModel info = new AuthInfoModel();
//        try {
//            String str = new String(Base64.getDecoder().decode(authToken));
//            String str2 = AESUtil.decrypt(AESUtil.AUTHTOKEN_AES_KEY, str);
//            String[] arr = str2.split("__");
//            if (arr.length < 4) {
//                throw new UserException(ReturnCodeModel.UNAUTHORIZED);
//            }
//            List<String> resultList = Arrays.asList(arr);
//            info.setUserId(resultList.get(1));
//            info.setDeviceId(resultList.get(2));
//            info.setOsType(resultList.get(3));
//        } catch (Exception e) {
//            LogUtil.error(logger, e, "解析authToken出错");
//            throw new UserException(ReturnCodeModel.UNAUTHORIZED);
//        }
//        return info;
//    }
//    /**
//     * 从缓存中检查当前用户的登录状态，超过时间则抛出AuthToken失效异常
//     *
//     * @param info
//     * @return
//     */
//    private void checkAuthToken(AuthInfoModel info) {
//        AuthInfoModel redisInfo = userCacheUtil.getAuthTokenCache(info.getUserId());
//        //如果缓存中不存在，则认为它失效
//        if (redisInfo == null) {
//            throw new UserException(ReturnCodeModel.AUTHTOKEN_INVALID);
//        }
//        //判断终端登录
//        if (!redisInfo.getDeviceId().equals(info.getDeviceId())) {
//            throw new UserException(ReturnCodeModel.DEVICE_INVALID);
//        }
//        long nowTimeDiff = new Date().getTime() - redisInfo.getTokenTime();
//        // 时间超过四天则刷新缓存失效时间
//        if (nowTimeDiff > (4 * OcsParam.ocsTimeOut * 1000)) {
//            muCacheUtil.saveAuthTokenCache(redisInfo.getUserId(), redisInfo.getDeviceId(),redisInfo.getOsType());
//        }
//    }
//
//    private String randomStr(int len) {
//        StringBuilder result = new StringBuilder();
//        String chars = "abcdefghijklmnopqrstuvwxyz";
//        for (int i = 0; i < len; i++) {
//            int index = (int) (Math.random() * 26);
//            result.append(chars.charAt(index));
//        }
//        return result.toString();
//    }
//
//}
