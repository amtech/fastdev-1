package cn.lucode.fastdev.user.model;

import java.io.Serializable;

/**
 * @author yunfeng.lu
 * @create 2017/12/24.
 */
public class LoginReq implements Serializable{

    private static final long serialVersionUID = 673545001940919643L;
    private String loginName;
    private String password;

    @Override
    public String toString() {
        return "LoginReq{" +
                "loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
