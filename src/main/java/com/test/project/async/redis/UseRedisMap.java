package com.test.project.async.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class UseRedisMap implements UseRedis {

    @Autowired
    private final StringRedisTemplate redisTemplate;

    public UseRedisMap(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String getValue(String key, String hashKey) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        return (String) hashOperations.get(key, hashKey);
    }

    @Override
    public void setValue(String dest, String key) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key , dest , " success("+dest+") ");
    }

    @Override
    public boolean checkNext(String key) {
        if (!getValue(key, "A").isEmpty() &&
                !getValue(key, "B").isEmpty() &&
                !getValue(key, "C").isEmpty() ) {
            return true;
        }
        return false;
    }
}
