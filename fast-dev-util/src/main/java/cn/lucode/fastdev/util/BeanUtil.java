package cn.lucode.fastdev.util;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.*;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author yunfeng.lu
 * @create 2017/10/29.
 */
public class BeanUtil {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);
    private static final FastDateFormat date_format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    static BeanUtilsBean beanUtilsBean = new BeanUtilsBean();

    static {
        beanUtilsBean.getConvertUtils().register(new IntegerConverter(null), Integer.class);
        beanUtilsBean.getConvertUtils().register(new LongConverter(null), Long.class);
        beanUtilsBean.getConvertUtils().register(new DoubleConverter(null), Double.class);
        beanUtilsBean.getConvertUtils().register(new StringConverter(null), String.class);
        beanUtilsBean.getConvertUtils().register(new DateConverter(null), Date.class);
        beanUtilsBean.getConvertUtils().register(new BigDecimalConverter(null), BigDecimal.class);
    }

    public static Object map2Bean(Map map, Class tClass) throws IllegalAccessException, InstantiationException {

        Object bean = tClass.newInstance();
        if (bean == null || map == null) {
            return null;
        }
        Field[] fds = bean.getClass().getDeclaredFields();
        for (Field des : fds) {
            String prop = des.getName();
            String key = null;
            if ((key = setIgnoreCaseContains(map.keySet(), prop)) != null) {
                try {
                    beanUtilsBean.setProperty(bean, prop, map.get(key));
                } catch (IllegalAccessException e) {
                    LogUtil.error(logger, e, "Map 转换 Bean失败 入参{0}", map);
                } catch (InvocationTargetException e) {
                    LogUtil.error(logger, e, "Map 转换 Bean失败 入参{0}", map);
                }
            }
        }
        return bean;
    }

    /**
     * @param listSourc
     * @return
     */
    public static List beanList2mapList(List listSourc) {
        if (listSourc == null || listSourc.isEmpty()) {
            return new ArrayList();
        }
        List listTarget = new ArrayList();
        try {
            for (int i = 0; i < listSourc.size(); i++) {
                Object bean = bean2Map(listSourc.get(i));
                listTarget.add(bean);
            }
        } catch (Exception e) {
            LogUtil.error(logger, e, "list互相赋值报错");
        }
        return listTarget;
    }

    public static List mapList2BeanList(List<Map> listSourc, Class tClass) {
        if (listSourc == null || listSourc.isEmpty()) {
            return new ArrayList();
        }
        List listTarget = new ArrayList();
        try {
            for (int i = 0; i < listSourc.size(); i++) {
                Object bean = map2Bean(listSourc.get(i), tClass);
                listTarget.add(bean);
            }
        } catch (Exception e) {
            LogUtil.error(logger, e, "list互相赋值报错");
        }
        return listTarget;
    }

    public static Map bean2Map(Object obj) {

        if (obj == null) {
            return null;
        }
        Map map = new HashMap();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if (value instanceof Date) {
                        map.put(key, date_format.format(value));
                    } else {
                        map.put(key, String.valueOf(value));
                    }
                }

            }
        } catch (Exception e) {
            LogUtil.error(logger, e, "Bean 转换 Map 失败 入参{0}", obj);
        }

        return map;

    }

    public static Map bean2MapWithoutNull(Object obj) {
        if (obj == null) {
            return null;
        }
        Map map = new HashMap();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if (value != null) {
                        if (value instanceof Date) {
                            map.put(key, date_format.format(value));
                        } else {
                            map.put(key, String.valueOf(value));
                        }
                    }
                }

            }
        } catch (Exception e) {
            LogUtil.error(logger, e, "Bean 转换 Map 失败 入参{0}", obj);
        }

        return map;

    }

    private static String setIgnoreCaseContains(Set<String> set, String prop) {
        for (String str : set) {
            if (str.replaceAll("_", "").equalsIgnoreCase(prop)) {
                return str;
            }
        }
        return null;
    }

    public static List beanList2mapListWithoutNull(List listSourc) {
        if (listSourc == null || listSourc.isEmpty()) {
            return new ArrayList();
        }
        List listTarget = new ArrayList();
        try {
            for (int i = 0; i < listSourc.size(); i++) {
                Object bean = bean2MapWithoutNull(listSourc.get(i));
                listTarget.add(bean);
            }
        } catch (Exception e) {
            LogUtil.error(logger, e, "list互相赋值报错");
        }
        return listTarget;
    }
}
