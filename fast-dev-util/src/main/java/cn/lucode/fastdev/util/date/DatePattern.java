package cn.lucode.fastdev.util.date;

/**
 * 常用的时间格式
 * @author yunfeng.lu
 * @create 2017/11/22.
 */
public class DatePattern {
    /**
     * 标准日期格式
     */
    public final static String NORM_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 标准时间格式
     */
    public final static String NORM_TIME_PATTERN = "HH:mm:ss";
    /**
     * 标准日期时间格式，精确到分
     */
    public final static String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    /**
     * 标准日期时间格式，精确到秒
     */
    public final static String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 标准日期时间格式，精确到毫秒
     */
    public final static String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * HTTP头中日期时间格式
     */
    public final static String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";
}

