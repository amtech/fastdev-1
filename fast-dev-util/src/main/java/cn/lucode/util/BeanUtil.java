package cn.lucode.util;

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

    public BeanUtil() {
    }

    public static Object map2Bean(Map map, Class tClass) throws IllegalAccessException, InstantiationException {
        Object bean = tClass.newInstance();
        if(bean != null && map != null) {
            Field[] fds = bean.getClass().getDeclaredFields();
            Field[] arr$ = fds;
            int len$ = fds.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Field des = arr$[i$];
                String prop = des.getName();
                String key = null;
                if((key = setIgnoreCaseContains(map.keySet(), prop)) != null) {
                    try {
                        beanUtilsBean.setProperty(bean, prop, map.get(key));
                    } catch (IllegalAccessException var11) {
                        LogUtil.error(logger, var11, "Map 转换 Bean失败 入参{0}", new Object[]{map});
                    } catch (InvocationTargetException var12) {
                        LogUtil.error(logger, var12, "Map 转换 Bean失败 入参{0}", new Object[]{map});
                    }
                }
            }

            return bean;
        } else {
            return null;
        }
    }

    public static List beanList2mapList(List listSourc) {
        if(listSourc != null && !listSourc.isEmpty()) {
            ArrayList listTarget = new ArrayList();

            try {
                for(int i = 0; i < listSourc.size(); ++i) {
                    Object bean = bean2Map(listSourc.get(i));
                    listTarget.add(bean);
                }
            } catch (Exception var4) {
                LogUtil.error(logger, var4, "list互相赋值报错", new Object[0]);
            }

            return listTarget;
        } else {
            return new ArrayList();
        }
    }

    public static List mapList2BeanList(List<Map> listSourc, Class tClass) {
        if(listSourc != null && !listSourc.isEmpty()) {
            ArrayList listTarget = new ArrayList();

            try {
                for(int i = 0; i < listSourc.size(); ++i) {
                    Object bean = map2Bean((Map)listSourc.get(i), tClass);
                    listTarget.add(bean);
                }
            } catch (Exception var5) {
                LogUtil.error(logger, var5, "list互相赋值报错", new Object[0]);
            }

            return listTarget;
        } else {
            return new ArrayList();
        }
    }

    public static Map bean2Map(Object obj) {
        if(obj == null) {
            return null;
        } else {
            HashMap map = new HashMap();

            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                PropertyDescriptor[] arr$ = propertyDescriptors;
                int len$ = propertyDescriptors.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    PropertyDescriptor property = arr$[i$];
                    String key = property.getName();
                    if(!key.equals("class")) {
                        Method getter = property.getReadMethod();
                        Object value = getter.invoke(obj, new Object[0]);
                        if(value instanceof Date) {
                            map.put(key, date_format.format(value));
                        } else {
                            map.put(key, String.valueOf(value));
                        }
                    }
                }
            } catch (Exception var11) {
                LogUtil.error(logger, var11, "Bean 转换 Map 失败 入参{0}", new Object[]{obj});
            }

            return map;
        }
    }

    private static String setIgnoreCaseContains(Set<String> set, String prop) {
        Iterator i$ = set.iterator();

        String str;
        do {
            if(!i$.hasNext()) {
                return null;
            }

            str = (String)i$.next();
        } while(!str.replaceAll("_", "").equalsIgnoreCase(prop));

        return str;
    }

    static {
        beanUtilsBean.getConvertUtils().register(new IntegerConverter((Object)null), Integer.class);
        beanUtilsBean.getConvertUtils().register(new LongConverter((Object)null), Long.class);
        beanUtilsBean.getConvertUtils().register(new DoubleConverter((Object)null), Double.class);
        beanUtilsBean.getConvertUtils().register(new StringConverter((Object)null), String.class);
        beanUtilsBean.getConvertUtils().register(new DateConverter((Object)null), Date.class);
        beanUtilsBean.getConvertUtils().register(new BigDecimalConverter((Object)null), BigDecimal.class);
    }
}
