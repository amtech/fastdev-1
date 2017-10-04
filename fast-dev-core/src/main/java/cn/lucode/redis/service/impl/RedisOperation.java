package cn.lucode.redis.service.impl;

import cn.lucode.redis.service.IRedisOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by yunfeng.lu on 2017/10/4.
 */
@Service
public class RedisOperation implements IRedisOperation{
    private static final long DEFAULT_REDIS_KEYS_TIMEOUT = 60*60*24*7;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void setValue(String key, String value) {
        this.setValue(key,value,DEFAULT_REDIS_KEYS_TIMEOUT);
    }

    @Override
    public void setValue(String key, String value, Long timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout , TimeUnit.SECONDS);
    }

    @Override
    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public List<String> getValues(String prex) {
        Set<String> keys = stringRedisTemplate.keys(prex + "*");
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public void removeV(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public void setSetValue(String key, Long timeout, String... values) {

        if(timeout == null)
        {
            timeout = DEFAULT_REDIS_KEYS_TIMEOUT;
        }

        stringRedisTemplate.opsForSet().add(key,values);

        stringRedisTemplate.expire(key,timeout,TimeUnit.SECONDS);
    }

    @Override
    public Set<String> getSetValues(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    @Override
    public void setHashValue(String key, String hashKey, String hashValue) {
        stringRedisTemplate.opsForHash().put(key,hashKey,hashValue);
    }

    @Override
    public String getHashValue(String key, String hashKey) {
        return String.valueOf(stringRedisTemplate.opsForHash().get(key,hashKey));
    }

    @Override
    public  List mutiGetHashValue(String key, List hashKeys) {
        return stringRedisTemplate.opsForHash().multiGet(key,hashKeys);
    }

    @Override
    public Set getHashKeys(String key) {
        return stringRedisTemplate.opsForHash().keys(key);
    }

    @Override
    public void removeHashEntry(String key, String... hashKey) {
        stringRedisTemplate.opsForHash().delete(key,hashKey);
    }
}
