package cn.lucode.fastdev.util;

import cn.hutool.core.collection.CollUtil;

import java.util.Collection;
import java.util.List;

/**
 * 集合工具类
 *
 * @author yunfeng.lu
 * @create 2018/2/17.
 */
public class CollectionUtils {

    /**
     * str 字符串是否包含在 这个集合中
     * @param str
     * @param collection
     * @return
     */
    public static boolean isContainOneString(String str, Collection<?> collection) {
        boolean flag = false;
        if (!CollectionUtils.isEmpty(collection) && !StringUtil.isNil(str)) {
            for (Object o : collection) {
                if (str.equals(o.toString())) {
                    flag = true;
                    return flag;
                } else {
                    return flag;
                }
            }
        }
        return flag;
    }


    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
