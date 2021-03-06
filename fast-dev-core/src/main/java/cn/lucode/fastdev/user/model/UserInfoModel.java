package cn.lucode.fastdev.user.model;

import cn.lucode.fastdev.user.dal.pojo.User;

import java.io.Serializable;

/**
 * @author yunfeng.lu
 * @create 2017/12/24.
 */
public class UserInfoModel implements Serializable{
    private static final long serialVersionUID = -2992913120247672694L;

    private String userId;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户性别--m:男性 f:女性 u:未知
     */
    private String gender;

    /**
     * 用户角色
     */
    private String userRoleId;

    /**
     * 用户手机号
     */
    private String userMobile;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 账号状态--0:冻结 1:正常
     */
    private Integer userEnableStatus;

    public static UserInfoModel pojo2Model(User user){
        if(user==null){
            return null;
        }
        UserInfoModel model=new UserInfoModel();
        model.setUserId(user.getUserId());
        model.setAvatar(user.getAvatar());
        model.setNickname(user.getNickname());
        model.setGender(user.getGender());
        model.setLoginName(user.getLoginName());
        model.setUserEmail(user.getUserEmail());
        model.setUserMobile(user.getUserMobile());
        model.setUserRoleId(user.getUserRoleId());
        model.setUserEnableStatus(user.getUserEnableStatus());
        return model;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserEnableStatus() {
        return userEnableStatus;
    }

    public void setUserEnableStatus(Integer userEnableStatus) {
        this.userEnableStatus = userEnableStatus;
    }
}
