package cn.lucode.fastdev.redis.service;


import java.util.List;
import java.util.Set;

/**
 * Created by yunfeng.lu on 2017/10/4.
 */
public interface IRedisOperation {
    void setValue(String  key,String value);
    void setSetValue(String key,Long timeout,String... values);

    /**
     * 添加SET类型值
     * @param key
     * @param hashKey
     * @param hashValue
     */
    void setHashValue(String key,String hashKey,String hashValue);
    void setValue(String  key,String value,Long timeout);
    String  getValue(String key);


    /**
     * 设置set类型下的所有ITEM
     * @param key
     * @return
     */
    Set<String> getSetValues(String key);

    /**
     * 获取指定的hash key下面的结果
     * @param key
     * @param hashKey
     * @return
     */
    String  getHashValue(String key,String hashKey);

    /**
     * 批量获取执行的hash value
     * @param key
     * @param hashKeys
     * @return
     */
    List<String> mutiGetHashValue(String key,List<String> hashKeys);

    /**
     * 批量删除hash key
     * @param key
     * @param hashKey
     */
    void removeHashEntry(String key,String... hashKey);

    /**
     * 获取当前key下面的所有hash keys
     * @param key
     * @return
     */
    Set<String> getHashKeys(String key);


    List<String> getValues(String prex);

    void removeV(String key);

}