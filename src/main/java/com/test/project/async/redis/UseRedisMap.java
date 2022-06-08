package com.test.project.async.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
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
        hashOperations.put(key, dest, " success(" + dest + ") ");
    }

    @Override
    public boolean checkNextAble(String key) {
        if (getValue(key, "A") != null &&
                getValue(key, "B") != null &&
                getValue(key, "C") != null) {
            return true;
        }
        return false;
    }
}
