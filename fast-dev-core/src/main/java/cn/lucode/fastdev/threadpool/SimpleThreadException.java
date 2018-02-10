package cn.lucode.fastdev.threadpool;

/**
 * @author yunfeng.lu
 * @create 2018/2/9.
 */
public class SimpleThreadException extends RuntimeException{

    private static final long serialVersionUID = -4002347717836247561L;

    private String resCode ;  //异常对应的返回码
    private String resMsg;  //异常对应的描述信息

    public SimpleThreadException() {
        super();
    }

    public SimpleThreadException(String message) {
        super(message);
        resMsg = message;
    }

    public SimpleThreadException(String resCode, String resMsg) {
        super();
        this.resCode = resCode;
        this.resMsg = resMsg;
    }

    public String getResCode() {
        return resCode;
    }

    public String getResMsg() {
        return resMsg;
    }
}