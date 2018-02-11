package cn.lucode.fastdev.verify.service;

import cn.lucode.fastdev.verify.EmailModel;

/**
 * @author yunfeng.lu
 * @create 2018/2/9.
 */

public interface QQEmailService {



    /**
     * 发送邮件
     * @param emailModel
     * @throws Exception
     */
    void sendAttachmentsMail(EmailModel emailModel) throws Exception;


}
