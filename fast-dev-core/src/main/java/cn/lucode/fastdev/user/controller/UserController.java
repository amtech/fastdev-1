package cn.lucode.fastdev.user.controller;

import cn.lucode.fastdev.common.CommonBizTypeCode;
import cn.lucode.fastdev.common.CommonResponseModel;
import cn.lucode.fastdev.user.model.LoginReq;
import cn.lucode.fastdev.user.service.UserService;
import cn.lucode.fastdev.user.utils.UserException;
import cn.lucode.fastdev.util.LogUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yunfeng.lu
 * @create 2017/12/4.
 */
@RestController
@RequestMapping("/user")
@Api("用户")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;


    @PostMapping("/login")
    @ApiOperation(value = "登陆",response =CommonResponseModel.class)
    public Object login(LoginReq loginReq){

        try {

            return CommonResponseModel.success(userService.login(loginReq));

        } catch (UserException ue) {

            LogUtil.error(logger, ue, "{0} 异常，请检查....{1}", CommonBizTypeCode.BIZ_USER.getDesc(),loginReq);

            return CommonResponseModel.facade(ue.getException_type());

        } catch (Exception ex) {

            LogUtil.error(logger, ex, "{0} 异常，请检查....{1}",CommonBizTypeCode.BIZ_USER.getDesc(),loginReq);

            return CommonResponseModel.fail();
        }

    }

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "得到用户信息，检查登录态",response =CommonResponseModel.class)
    public Object getUserInfo(@ApiParam(value = "authToken")
                                  @RequestParam(value = "authToken") String authToken){
        try {

            return CommonResponseModel.success(userService.getUserInfo(authToken));

        } catch (UserException ue) {

            LogUtil.error(logger, ue, "{0} 异常，请检查....{1}", CommonBizTypeCode.BIZ_USER.getDesc(),authToken);

            return CommonResponseModel.facade(ue.getException_type());

        } catch (Exception ex) {

            LogUtil.error(logger, ex, "{0} 异常，请检查....{1}",CommonBizTypeCode.BIZ_USER.getDesc(),authToken);

            return CommonResponseModel.fail();
        }

    }






}
