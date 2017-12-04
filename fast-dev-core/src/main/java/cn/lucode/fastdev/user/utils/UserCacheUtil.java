package cn.lucode.fastdev.user.utils;

import cn.lucode.fastdev.redis.OcsParam;
import cn.lucode.fastdev.redis.service.IRedisOperation;
import cn.lucode.fastdev.user.model.AuthInfoModel;
import cn.lucode.fastdev.util.JsonUtil;
import cn.lucode.fastdev.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yunfeng.lu
 * @create 2017/12/4.
 */
@Component
@Scope("singleton")
public class UserCacheUtil {
    @Autowired
    private IRedisOperation redisOperation;

    private String KNOWN="known";

    /**
     * 保存登录状态缓存,1天有效
     */
    public void saveAuthTokenCache(AuthInfoModel authInfoModel) {
        if (StringUtil.isEmpty(authInfoModel.getDeviceId())){
            authInfoModel.setDeviceId(KNOWN);
        }
        if (StringUtil.isEmpty(authInfoModel.getIdentity())){
            authInfoModel.setIdentity(KNOWN);
        }
        if (StringUtil.isEmpty(authInfoModel.getIp())){
            authInfoModel.setIp(KNOWN);
        }
        if (StringUtil.isEmpty(authInfoModel.getOsType())){
            authInfoModel.setOsType(KNOWN);
        }
        if(StringUtil.isEmpty(authInfoModel.getSourceId())){
            authInfoModel.setSourceId(KNOWN);
        }

        authInfoModel.setTokenTime(new Date().getTime());
        redisOperation.setValue(OcsParam.AUTH_TOKEN + "_" + authInfoModel.getUserId(),
                JsonUtil.obj2Json(authInfoModel), OcsParam.OCSPARAM_HOUR);
    }

    /**
     * 获取登录状态缓存
     *
     * @param userId
     */
    public AuthInfoModel getAuthTokenCache(String userId) {
        String redisVal = redisOperation.getValue(OcsParam.AUTH_TOKEN + "_" + userId);
        //如果缓存中不存在，则认为它失效
        if (StringUtil.isEmpty(redisVal)) {
            return null;
        }
        return JsonUtil.json2Bean(redisVal, AuthInfoModel.class);
    }


}
