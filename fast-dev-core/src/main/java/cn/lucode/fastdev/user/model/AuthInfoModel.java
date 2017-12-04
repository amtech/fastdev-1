package cn.lucode.fastdev.user.model;

import java.io.Serializable;

/**
 * @author yunfeng.lu
 * @create 2017/12/5.
 */
public class AuthInfoModel implements Serializable {


    private static final long serialVersionUID = -5474669756984241365L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 设备编号
     */
    private String deviceId;

    /**
     * 设备类型
     */
    private String osType;

    /**
     * Auth Token创建时间戳
     */
    private Long tokenTime;

    /**
     * 登陆ip
     */
    private String ip;

    /**
     * 登录源
     */
    private String sourceId;

    /**
     * 权限身份
     */
    private String identity;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public Long getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(Long tokenTime) {
        this.tokenTime = tokenTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "AuthInfoRes{" +
                "userId='" + userId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", osType='" + osType + '\'' +
                ", tokenTime=" + tokenTime +
                ", ip='" + ip + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", identity='" + identity + '\'' +
                '}';
    }
}