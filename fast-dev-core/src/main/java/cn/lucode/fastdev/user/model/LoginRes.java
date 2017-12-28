package cn.lucode.fastdev.user.model;

import java.io.Serializable;

/**
 * @author yunfeng.lu
 * @create 2017/12/24.
 */
public class LoginRes implements Serializable{

    private static final long serialVersionUID = 478422912438269501L;
    private String authToken;
    private UserInfoModel userInfo;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public UserInfoModel getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoModel userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "LoginRes{" +
                "authToken='" + authToken + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
