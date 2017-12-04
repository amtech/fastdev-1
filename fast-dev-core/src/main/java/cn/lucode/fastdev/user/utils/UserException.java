package cn.lucode.fastdev.user.utils;

import cn.lucode.fastdev.common.ReturnCodeModel;
import cn.lucode.fastdev.exception.CommonException;

/**
 * @author yunfeng.lu
 * @create 2017/12/4.
 */
public class UserException extends CommonException {
    private static final long serialVersionUID = 5719639814985692478L;


    private ReturnCodeModel exception_type;
    public UserException(ReturnCodeModel type) {
        super(type);
        this.exception_type = type;
    }

    public String getErrorCode() {
        return this.exception_type.getCode();
    }

    public String getErrorDeclare() {
        return this.exception_type.getMsg();
    }

    public ReturnCodeModel getException_type() {
        return exception_type;
    }

}
