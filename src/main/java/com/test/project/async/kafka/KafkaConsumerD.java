package com.test.project.async.kafka;

import com.test.project.async.redis.UseRedis;
import com.test.project.async.service.ProcessSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerD {

    private final ProcessSend processSend;
    private final UseRedis useRedis;


    public KafkaConsumerD(ProcessSend processSend, UseRedis useRedis) {
        this.processSend = processSend;
        this.useRedis = useRedis;
    }


    @KafkaListener(topics = {"testD"} , errorHandler = "kafkaExceptionHandler")
    public void receiveMessageD(String message) throws InterruptedException {
        log.info("[Test] Kafka Consumer message : {}", message);
        // key, date, uuid, message, nextSeq

        String[] subMessage = message.split("/");
        String uuid = subMessage[2];

        Thread.sleep(4500L);

        if (!useRedis.checkNextAble(uuid)) {
            processSend.sendCom(uuid, subMessage[1], Long.valueOf(subMessage[4]), subMessage[3] , "D");
        }
    }

}