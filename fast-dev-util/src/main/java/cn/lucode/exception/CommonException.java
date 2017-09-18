package cn.lucode.exception;

/**
 * Created by yunfeng.lu on 2017/9/18.
 */
public class CommonException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public CommonException(String message) {
        super(message);
    }
}
