package com.test.project.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerC {

    private final ProcessC processC;
    private final StringRedisTemplate stringRedisTemplate;
    private final SendService sendService;

    public KafkaConsumerC(ProcessC processC
            , StringRedisTemplate stringRedisTemplate
            , SendService sendService) {
        this.processC = processC;
        this.stringRedisTemplate = stringRedisTemplate;
        this.sendService = sendService;
    }


    @KafkaListener(topics = {"testC"})
    public void receiveMessageC(String message) throws InterruptedException {
        log.info("[Test] Kafka Consumer message : {}", message);
        // key, date, uuid, message, nextSeq

        String[] subMessage = message.split("/");
        String uuid = subMessage[2];

        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();

        processC.saveC(subMessage[1], uuid);

        if(stringValueOperations.get(uuid) == null){
            stringValueOperations.set(uuid, "1");
        }else if(stringValueOperations.get(uuid).equals("2")){
            sendService.sendCom(subMessage[1], Long.parseLong(subMessage[4]), subMessage[3] + "by C");
            log.info("uuid[{}] is done by C", uuid);
        }else{
            stringValueOperations.set(uuid, String.valueOf(Integer.parseInt(stringValueOperations.get(uuid)) + 1));
        }
    }

}