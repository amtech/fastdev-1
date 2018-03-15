package cn.lucode.fastdev.util;

import java.text.MessageFormat;
import org.slf4j.Logger;

/**
 * 统一日志打印工具
 *
 * @author windy
 * @version $Id: LogUtil.java, v 0.1 2017-11-30 下午03:35:43 multiple persons Exp $
 */
public final class LogUtil {

    public final static String LEFT_TAG  = "[";
    public final static String RIGHT_TAG = "]";
    public final static String CONNECTOR = "-";
    public final static String COMMA     = ",";

    /**
     * 禁用构造函数
     */
    private LogUtil() {
    }

    /**
     * <p>生成调试级别日志</p>
     * <p>
     * 根据带参数的日志模板和参数集合，生成debug级别日志
     * 带参数的日志模板中以{数字}表示待替换为变量的日志点，如a={0}，表示用参数集合中index为0的参数替换{0}处
     * </p>
     * @param logger        logger引用
     * @param template      带参数的日志模板
     * @param parameters    参数集合
     */
    public static void debug(Logger logger, String template, Object... parameters) {
        if (logger.isDebugEnabled()) {
            logger.debug(getLogString(template, parameters));
        }
    }

    /**
     * <p>生成通知级别日志</p>
     * <p>
     * 根据带参数的日志模板和参数集合，生成info级别日志
     * 带参数的日志模板中以{数字}表示待替换为变量的日志点，如a={0}，表示用参数集合中index为0的参数替换{0}处
     * </p>
     * @param logger        logger引用
     * @param template      带参数的日志模板
     * @param parameters    参数集合
     */
    public static void info(Logger logger, String template, Object... parameters) {
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(template, parameters));
        }
    }

    /**
     * <p>生成警告级别日志</p>
     * <p>
     * 根据带参数的日志模板和参数集合，生成warn级别日志
     * 带参数的日志模板中以{数字}表示待替换为变量的日志点，如a={0}，表示用参数集合中index为0的参数替换{0}处
     * </p>
     * @param logger        logger引用
     * @param template      带参数的日志模板
     * @param parameters    参数集合
     */
    public static void warn(Logger logger, String template, Object... parameters) {
        logger.warn(getLogString(template, parameters));
    }

    /**
     * <p>生成警告级别日志</p>
     * <p>
     * 根据带参数的日志模板和参数集合，生成warn级别日志
     * 带参数的日志模板中以{数字}表示待替换为变量的日志点，如a={0}，表示用参数集合中index为0的参数替换{0}处
     * </p>
     * @param e             错误异常堆栈
     * @param logger        logger引用
     * @param template      带参数的日志模板
     * @param parameters    参数集合
     */
    public static void warn(Logger logger, Throwable e, String template, Object... parameters) {
        logger.warn(getLogString(template, parameters), e);
    }

    /**
     * <p>生成错误级别日志</p>
     * <p>
     * 根据带参数的日志模板和参数集合，生成error级别日志
     * 带参数的日志模板中以{数字}表示待替换为变量的日志点，如a={0}，表示用参数集合中index为0的参数替换{0}处
     * </p>
     * @param e             错误异常堆栈
     * @param logger        logger引用
     * @param template      带参数的日志模板
     * @param parameters    参数集合
     */
    public static void error(Logger logger, Throwable e, String template, Object... parameters) {
        logger.error(getLogString(template, parameters), e);
    }

    /**
     * <p>生成错误级别日志</p>
     * <p>
     * 根据带参数的日志模板和参数集合，生成error级别日志
     * 带参数的日志模板中以{数字}表示待替换为变量的日志点，如a={0}，表示用参数集合中index为0的参数替换{0}处
     * </p>
     * @param logger        logger引用
     * @param template      带参数的日志模板
     * @param parameters    参数集合
     */
    public static void error(Logger logger, String template, Object... parameters) {
        logger.error(getLogString(template, parameters));
    }

    /**
     * 拼装内容
     * @param template
     * @param parameters
     * @return
     */
    private static String getLogString(String template, Object... parameters) {
        StringBuffer log = new StringBuffer();
        log.append(MessageFormat.format(template, parameters));
        return log.toString();
    }


}
