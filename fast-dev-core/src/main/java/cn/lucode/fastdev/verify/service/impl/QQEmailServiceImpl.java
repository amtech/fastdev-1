package cn.lucode.fastdev.verify.service.impl;

import cn.lucode.fastdev.threadpool.DefaultPoolManager;
import cn.lucode.fastdev.util.HTMLTemplateUtils;
import cn.lucode.fastdev.util.StringUtil;
import cn.lucode.fastdev.verify.EmailModel;
import cn.lucode.fastdev.verify.service.QQEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yunfeng.lu
 * @create 2018/2/9.
 */

@Service
public class QQEmailServiceImpl implements QQEmailService{

    private static final Logger logger = LoggerFactory.getLogger(QQEmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private HTMLTemplateUtils htmlTemplateUtils;

    @Autowired
    DefaultPoolManager defaultPoolManager;


    /**
     * @param emailModel
     * @throws Exception
     */
    public void sendAttachmentsMail(EmailModel emailModel) throws Exception {
        if(emailModel!=null){
            if(StringUtil.isNil(emailModel.getSender())){
                // 默认发送人
                emailModel.setSender("676692273@qq.com");
            }
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailModel.getSender());
            helper.setTo(emailModel.getReceiver());
            helper.setSubject("lucode");
            Map<String, Object> params = new HashMap<>();
            params.put("receiver", emailModel.getReceiver());
            params.put("verifCode", emailModel.getVerifCode());
            helper.setText(htmlTemplateUtils.render(emailModel.getTemplateType(), params),true);
            defaultPoolManager.getAnsycTaskExecutor().execute(() -> {
                // 采用异步方式
                mailSender.send(mimeMessage);
            });

        }

    }


}
