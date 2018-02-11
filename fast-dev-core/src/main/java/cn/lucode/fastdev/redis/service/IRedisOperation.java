package cn.lucode.fastdev.redis.service;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by yunfeng.lu on 2017/10/4.
 */
public interface IRedisOperation {
    void setValue(String key, String value);

    void setValue(String key, String value, Long timeout,TimeUnit unit);
    /**
     * 添加SET类型值
     *
     * @param key
     * @param timeout
     * @param values
     */
    void setSetValue(String key, Long timeout, String... values);

    String getValue(String key);

    /**
     *
     *
     * @param key
     * @return
     */
    Set<String> getSetValues(String key);

    /**
     * 设置 一个 key 和hashkey 以及对应的 hashvalue
     * @param key
     * @param hashKey
     * @param hashValue
     */
    void setHashValue(String key, String hashKey, String hashValue);
    /**
     * 获取指定的hash key下面的结果
     *
     * @param key
     * @param hashKey
     * @return
     */
    String getHashValue(String key, String hashKey);

    /**
     * 批量获取执行的hash value
     *
     * @param key
     * @param hashKeys
     * @return
     */
    List<String> mutiGetHashValue(String key, List<String> hashKeys);

    /**
     * 批量获取 设置 hash
     * @param key
     * @param value
     */
    void setHashAll(String key, Map<String, Object> value);


    void setHashAll(String key, Map<String, Object> value, Long timeout,TimeUnit unit);

    /**
     * 获取整个 hash
     * @param key
     * @return
     */
    Map<Object, Object> getHash(String key);


    /**
     * 批量删除hash key
     *
     * @param key
     * @param hashKey
     */
    void removeHashEntry(String key, String... hashKey);


    /**
     * 删除set key
     */
    void removeSetEntry(String setkey, String setValue);

    /**
     * 获取当前key下面的所有hash keys
     *
     * @param key
     * @return
     */
    Set<String> getHashKeys(String key);


    List<String> getValues(String prex);

    void removeV(String key);

    boolean putIfAbsent(String key, String value);

    /**
     * 字节数组放入缓存
     * @param paramArrayOfByte1
     * @param paramArrayOfByte2
     */
    void putRedis(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    /**
     * 字节数组放入缓存，超时时间（秒为单位）
     * @param paramArrayOfByte1
     * @param paramArrayOfByte2
     * @param timeout
     */
    void putRedis(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, long timeout,TimeUnit unit);

    /**
     * 获取字节数组缓存
     * @param paramArrayOfByte1
     * @return
     */
    byte[] getRedis(byte[] paramArrayOfByte1);

    /**
     * 删除字节数组缓存
     * @param paramArrayOfByte
     */
    void removeRedis(byte[] paramArrayOfByte);

    /**
     *  累加
     * @param paramString
     * @return
     */
    long incr(String paramString);

    /**
     * 递减
     * @param paramString
     * @return
     */
    long decr(String paramString);

    /**
     * 按参数累加
     * @param paramString
     * @param paramLong
     * @return
     */
    long incrBy(String paramString, long paramLong);

    /**
     * 按参数递减
     * @param paramString
     * @param paramLong
     * @return
     */
    long decrBy(String paramString, long paramLong);

    /**
     * 累加并设置超时
     * @param paramString
     * @param timeOut
     * @param unit
     * @return
     */
    long incrEx(String paramString, long timeOut,TimeUnit unit);

    /**
     * 递减并设置超时
     * @param paramString
     * @param timeOut
     * @param unit
     * @return
     */
    long decrEx(String paramString, long timeOut,TimeUnit unit);

    /**
     * 按参数累加并设置超时
     * @param paramString
     * @param paramLong
     * @param timeOut
     * @param unit
     * @return
     */
    long incrByEx(String paramString, long paramLong, long timeOut,TimeUnit unit);

    /**
     * 按参数递减并设置超时
     * @param paramString
     * @param paramLong
     * @param timeOut
     * @param unit
     * @return
     */
    long decrByEx(String paramString, long paramLong, long timeOut,TimeUnit unit);

    /**
     * 设置 key 的超时时间  毫秒级
     * @param key
     * @param timeout
     */
    void setTimeout(String key,long timeout);

}