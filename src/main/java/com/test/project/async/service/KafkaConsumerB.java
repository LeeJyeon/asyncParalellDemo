package com.test.project.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerB {

    private final ProcessB processB;
    private final StringRedisTemplate stringRedisTemplate;
    private final SendService sendService;

    public KafkaConsumerB(ProcessB processB
            , StringRedisTemplate stringRedisTemplate
            , SendService sendService) {
        this.processB = processB;
        this.stringRedisTemplate = stringRedisTemplate;
        this.sendService = sendService;
    }


    @KafkaListener(topics = {"testB"})
    public void receiveMessageB(String message) throws InterruptedException {
        log.info("[Test] Kafka Consumer message : {}", message);
        // key, date, uuid, message, nextSeq

        String[] subMessage = message.split("/");
        String uuid = subMessage[2];

        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();

        processB.saveB(subMessage[1], uuid);

        if(stringValueOperations.get(uuid) == null){
            stringValueOperations.set(uuid, "1");
        }else if(stringValueOperations.get(uuid).equals("2")){
            sendService.sendCom(subMessage[1], Long.parseLong(subMessage[4]), subMessage[3] + "by B");
            log.info("uuid[{}] is done by B", uuid);
        }else{
            stringValueOperations.set(uuid, String.valueOf(Integer.parseInt(stringValueOperations.get(uuid)) + 1));
        }

    }

}