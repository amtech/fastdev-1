package cn.lucode.fastdev.user.model;

import java.io.Serializable;

/**
 * @author yunfeng.lu
 * @create 2018/2/4.
 */
public class RegisterReq implements Serializable {
    private static final long serialVersionUID = -6414459242699231680L;

    private String loginName;

    private String password;

    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RegisterReq{" +
                "loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
