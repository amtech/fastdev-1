package cn.lucode.fastdev.verify;

import cn.lucode.fastdev.enums.TemplateType;

import java.util.Map;

/**
 * @author yunfeng.lu
 * @create 2018/2/9.
 */
public class EmailModel {
    /**
     * 回调参数
     */
    private String url;
    /**
     * email 模板类型
     */
    private TemplateType templateType;
    private String sender;
    private String receiver;
    private Map<String,Object> exendParam;

    @Override
    public String toString() {
        return "EmailModel{" +
                "url='" + url + '\'' +
                ", templateType=" + templateType +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", exendParam=" + exendParam +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TemplateType getTemplateType() {
        return templateType;
    }

    public void setTemplateType(TemplateType templateType) {
        this.templateType = templateType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Map<String, Object> getExendParam() {
        return exendParam;
    }

    public void setExendParam(Map<String, Object> exendParam) {
        this.exendParam = exendParam;
    }
}
