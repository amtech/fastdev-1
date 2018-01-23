//package cn.lucode.fastdev.user.model;
//
//import cn.lucode.fastdev.user.dal.pojo.User;
//
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * @author yunfeng.lu
// * @create 2018/1/14.
// */
//public class UserModel implements Serializable{
//
//    private static final long serialVersionUID = -3612760591082949997L;
//
//    private String userId;
//    /**
//     * 登陆名
//     */
//    private String loginName;
//
//    /**
//     * 用户昵称
//     */
//    private String nickname;
//
//    /**
//     * 用户头像
//     */
//    private String avatar;
//
//    /**
//     * 用户性别--m:男性 f:女性 u:未知
//     */
//    private String gender;
//
//    /**
//     * 用户手机号
//     */
//    private String userMobile;
//
//    /**
//     * 用户邮箱
//     */
//    private String userEmail;
//
//    public static UserModel Pojo2vo(User user){
//        UserModel model=new UserInfoModel();
//
//
//
//    }
//
//    @Override
//    public String toString() {
//        return "UserModel{" +
//                "userId='" + userId + '\'' +
//                ", loginName='" + loginName + '\'' +
//                ", nickname='" + nickname + '\'' +
//                ", avatar='" + avatar + '\'' +
//                ", gender='" + gender + '\'' +
//                ", userMobile='" + userMobile + '\'' +
//                ", userEmail='" + userEmail + '\'' +
//                '}';
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getLoginName() {
//        return loginName;
//    }
//
//    public void setLoginName(String loginName) {
//        this.loginName = loginName;
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getUserMobile() {
//        return userMobile;
//    }
//
//    public void setUserMobile(String userMobile) {
//        this.userMobile = userMobile;
//    }
//
//    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }
//}
