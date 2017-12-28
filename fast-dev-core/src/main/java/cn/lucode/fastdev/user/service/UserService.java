package cn.lucode.fastdev.user.service;

import cn.lucode.fastdev.user.model.LoginReq;
import cn.lucode.fastdev.user.model.LoginRes;

/**
 * @author yunfeng.lu
 * @create 2017/12/4.
 */
public interface UserService {

    LoginRes login(LoginReq loginReq);
}
