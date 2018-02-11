package cn.lucode.fastdev.verify.controller;

import cn.lucode.fastdev.common.CommonResponseModel;
import cn.lucode.fastdev.enums.TemplateType;
import cn.lucode.fastdev.util.LogUtil;
import cn.lucode.fastdev.verify.EmailModel;
import cn.lucode.fastdev.verify.service.QQEmailService;
import cn.lucode.fastdev.verify.service.impl.QQEmailServiceImpl;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yunfeng.lu
 * @create 2018/2/9.
 */
@RestController
@RequestMapping("/send")
@Api("发送邮件测试接口")
public class SendController {

    private static final Logger logger = LoggerFactory.getLogger(SendController.class);
    @Autowired
    private QQEmailService qqEmailService;

    @PostMapping("/qqemail")
    public Object login(@RequestParam(value = "sendTo") String sendTo){

        try {
            EmailModel emailModel=new EmailModel();
            emailModel.setUrl("www.baidu.com");
            emailModel.setSender("676692273@qq.com");
            emailModel.setReceiver(sendTo);
            emailModel.setTemplateType(TemplateType.REGISTER);
            qqEmailService.sendAttachmentsMail(emailModel);
            return CommonResponseModel.success();

        } catch (Exception ex) {

            LogUtil.error(logger, ex, "请检查....{0}",sendTo);

            return CommonResponseModel.fail();
        }

    }


}
