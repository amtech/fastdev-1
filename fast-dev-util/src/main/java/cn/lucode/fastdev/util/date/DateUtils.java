package cn.lucode.fastdev.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yunfeng.lu
 * @create 2017/11/22.
 */
public class DateUtils {
    /**
     * 根据特定格式格式化日期
     * @param date 被格式化的日期
     * @param format 日期格式，常用格式见： {@link DatePattern}
     * @return 格式化后的字符串
     */
    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date 被格式化的日期
     * @return 格式化后的日期
     */
    public static String formatDateTime(Date date) {
        if(null == date){
            return null;
        }
        return DatePatternLocal.NORM_DATETIME_FORMAT.get().format(date);
    }

    /**
     * 格式 yyyy-MM-dd
     *
     * @param date 被格式化的日期
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date) {
        if(null == date){
            return null;
        }
        return DatePatternLocal.NORM_DATE_FORMAT.get().format(date);
    }
}
