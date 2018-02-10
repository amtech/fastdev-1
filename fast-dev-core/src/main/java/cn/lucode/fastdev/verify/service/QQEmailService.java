package cn.lucode.fastdev.verify.service;

import cn.lucode.fastdev.enums.TemplateType;
import cn.lucode.fastdev.util.HTMLTemplateUtils;
import cn.lucode.fastdev.verify.EmailModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yunfeng.lu
 * @create 2018/2/9.
 */

@Service
public class QQEmailService {

    private static final Logger logger = LoggerFactory.getLogger(QQEmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private HTMLTemplateUtils htmlTemplateUtils;

    private ThreadPoolExecutor threadPoolExecutor;

    @PostConstruct
    public void init() {
        Integer poolNumInit = 40;
        Integer queueNumInit = 200;

        threadPoolExecutor = new ThreadPoolExecutor(
                poolNumInit, poolNumInit, 1000L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(queueNumInit),
                new ThreadPoolExecutor.AbortPolicy());

    }


    /**
     * @param emailModel
     * @throws Exception
     */
    public void sendAttachmentsMail(EmailModel emailModel) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(emailModel.getSender());
        helper.setTo(emailModel.getReceiver());
        helper.setSubject("lucode");
        Map<String, Object> params = new HashMap<>();
        params.put("name", emailModel.getReceiver());
        params.put("url", emailModel.getUrl());
        helper.setText(htmlTemplateUtils.render(emailModel.getTemplateType(), params));
        // 采用异步方式
        mailSender.send(mimeMessage);
    }


}
