package cn.lucode.fastdev.alarm.model;

/**
 * @author yunfeng.lu
 * @create 2018/3/18.
 */
public class MessageModel {
    private String content;
    private String date;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
