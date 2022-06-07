package com.test.project.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProcessSend {

    private final StringRedisTemplate redisTemplate;
    private final SendService sendService;

    public ProcessSend(StringRedisTemplate redisTemplate, SendService sendService) {
        this.redisTemplate = redisTemplate;
        this.sendService = sendService;
    }

    public void sendCom(String uuid, String date, Long comNumber, String context, String dest) {

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        switch (dest) {
            case "A":
                valueOperations.increment(uuid, 1);
                break;
            case "B":
                valueOperations.increment(uuid, 3);
                break;
            case "C":
                valueOperations.increment(uuid, 5);
                break;
        }

        log.info("count up! value {} {}", dest ,  valueOperations.get(uuid));

        if (valueOperations.get(uuid).equals("9")) {
            try {
                sendService.sendCom(date, comNumber, context + dest);
                log.info("uuid[{}] is done", uuid);
            } catch (DataIntegrityViolationException e) {
                log.warn("dup error skip!");
            }
        }

    }
}
