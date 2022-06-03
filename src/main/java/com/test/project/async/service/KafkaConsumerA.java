package com.test.project.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerA {

    private final ProcessA processA;
    private final StringRedisTemplate stringRedisTemplate;
    private final SendService sendService;

    public KafkaConsumerA(ProcessA processA
            , StringRedisTemplate stringRedisTemplate
            , SendService sendService) {
        this.processA = processA;
        this.stringRedisTemplate = stringRedisTemplate;
        this.sendService = sendService;
    }


    @KafkaListener(topics = {"testA"})
    public void receiveMessageA(String message) throws InterruptedException {
        log.info("[Test] Kafka Consumer message : {}", message);
        // key, date, uuid, message, nextSeq

        String[] subMessage = message.split("/");
        String uuid = subMessage[2];

        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();

        processA.saveA(subMessage[1], uuid);

        // dict 에 없으면
        if(stringValueOperations.get(uuid) == null){
            stringValueOperations.set(uuid, "1");
        }else if(stringValueOperations.get(uuid).equals("2")){ // dict 에 A.B.C 모두 완료로 판단되면
            sendService.sendCom(subMessage[1], Long.parseLong(subMessage[4]), subMessage[3] + "by A");
            log.info("uuid[{}] is done by A", uuid);
        }else{ // 진행
            stringValueOperations.set(uuid, String.valueOf(Integer.parseInt(stringValueOperations.get(uuid)) + 1));
        }



    }

}