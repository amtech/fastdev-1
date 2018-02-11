package cn.lucode.fastdev.user.model;



/**
 * @author yunfeng.lu
 * @create 2017/12/24.
 */
public class RegisterRes extends LoginRes{

    private static final long serialVersionUID = 478422912438269501L;
    /**
     * 注册激活地址
     */
    private String confimUrl;

    /**
     * 邮箱服务商的登录地址 如 email.qq.com
     */
    private String emailLoginUrl;

    public String getConfimUrl() {
        return confimUrl;
    }

    public void setConfimUrl(String confimUrl) {
        this.confimUrl = confimUrl;
    }

    public String getEmailLoginUrl() {
        return emailLoginUrl;
    }

    public void setEmailLoginUrl(String emailLoginUrl) {
        this.emailLoginUrl = emailLoginUrl;
    }
}
