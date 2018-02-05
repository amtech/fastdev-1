package cn.lucode.fastdev.user.service;

import cn.lucode.fastdev.user.model.*;

/**
 * @author yunfeng.lu
 * @create 2017/12/4.
 */
public interface UserService {

    LoginRes login(LoginReq loginReq);

    RegisterRes register(RegisterReq registerReq);


    UserInfoModel getUserInfo(String authToken);


    void layout(String authToken);
}
