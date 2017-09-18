package cn.lucode.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by yunfeng.lu on 2017/9/18.
 */
public class JsonUtil {

    public static <T> List<T> json2BeanList(String json, Class<T> cls) {
        return JSON.parseArray(json, cls);
    }

    public static <T> T json2Bean(String json, Class<T> cls) {
        return JSON.parseObject(json, cls);
    }

    public static String obj2Json(Object obj) {
        String json = JSON.toJSONString(obj);
        return json;
    }
}