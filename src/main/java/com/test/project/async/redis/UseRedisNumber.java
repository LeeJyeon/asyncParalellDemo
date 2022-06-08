package com.test.project.async.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

//@Component
public class UseRedisNumber implements UseRedis{

    @Autowired
    private final StringRedisTemplate redisTemplate;

    public UseRedisNumber(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String getValue(String key, String hashKey) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    @Override
    public void setValue(String dest, String key) {

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        switch (dest) {
            case "A":
                valueOperations.increment(key, 1);
                break;
            case "B":
                valueOperations.increment(key, 3);
                break;
            case "C":
                valueOperations.increment(key, 5);
                break;
        }
    }

    @Override
    public boolean checkNextAble(String key) {
        if (getValue(key,"").equals("9")) {
            return true;
        }
        return false;
    }
}
