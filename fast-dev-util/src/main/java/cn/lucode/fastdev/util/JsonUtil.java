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

    /**
     * fastjson api 不要问为什么写在这里
     * 1 public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
     2 public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
     3 public static final T parseObject(String text, Class clazz); // 把JSON文本parse为JavaBean
     4 public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
     5 public static final List parseArray(String text, Class clazz); //把JSON文本parse成JavaBean集合
     6 public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
     7 public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
     8 public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
     */

    public static <T> List<T> json2BeanList(String json, Class<T> cls) {
        return JSON.parseArray(json, cls);
    }

    /**
     * 单个对象的转换
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String json, Class<T> cls) {
        return JSON.parseObject(json, cls);
    }

    public static String obj2JsonStr(Object obj) {
         return JSON.toJSONString(obj);
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