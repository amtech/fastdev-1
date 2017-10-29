package cn.lucode.util;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

/**
 * @author yunfeng.lu
 * @create 2017/10/26.
 */
public class DateFormatUtil {
    private static final FastDateFormat DATE_FORMAT01 = FastDateFormat.getInstance("yyyy-MM-dd");
    private static final FastDateFormat DATE_FORMAT02 = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");


    public static String format01(Date date) {
        String curDate = DATE_FORMAT01.format(date.getTime());
        return curDate;
    }

    public static String format02(Date date) {
        String curDate = DATE_FORMAT02.format(date.getTime());
        return curDate;
    }
}
