package cn.lucode.fastdev.user.model;

import java.io.Serializable;

/**
 * @author yunfeng.lu
 * @create 2017/12/24.
 */
public class UserInfoModel implements Serializable{
    private static final long serialVersionUID = -2992913120247672694L;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserInfoModel{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
