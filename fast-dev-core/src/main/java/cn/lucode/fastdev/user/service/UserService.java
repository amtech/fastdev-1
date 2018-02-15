package cn.lucode.fastdev.user.service;

import cn.lucode.fastdev.user.model.*;

/**
 * @author yunfeng.lu
 * @create 2017/12/4.
 */
public interface UserService {

    LoginRes login(LoginReq loginReq);

    String registerStep1(RegisterReq registerReq);

    void registerStep2(RegisterReq registerReq) ;


    UserInfoModel getUserInfoByAuthToken(String authToken);

    UserInfoModel getUserInfoById(String uid);

    void layout(String authToken);
}
