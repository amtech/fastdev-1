package cn.lucode.fastdev.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

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

    public static String map2JsonWriteMapNullValue(Map map) {
        String json = JSONObject.toJSONString(map, new SerializerFeature[]{SerializerFeature.WriteMapNullValue});
        return json;
    }

    public static String obj2JsonWriteNullValue(Object o) {
        return JSON.toJSONString(o, new SerializerFeature[]{SerializerFeature.WriteMapNullValue});
    }

    public static <T> Map<String, T> json2Map(String json) {
        return (Map)JSON.parse(json);
    }

    public static Object jsonObj2Obj(JSONObject jsonObject) {
        return jsonObject != null?JSONObject.parse(jsonObject.toJSONString()):null;
    }

}