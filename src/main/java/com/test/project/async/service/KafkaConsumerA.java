package com.test.project.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerA {

    private final ProcessA processA;
    private final ProcessSend processSend;


    public KafkaConsumerA(ProcessA processA, ProcessSend processSend) {
        this.processA = processA;
        this.processSend = processSend;
    }


    @KafkaListener(topics = {"testA"} , errorHandler = "kafkaExceptionHandler")
    public void receiveMessageA(String message) throws InterruptedException {
        log.info("[Test] Kafka Consumer message : {}", message);
        // key, date, uuid, message, nextSeq

        String[] subMessage = message.split("/");
        String uuid = subMessage[2];

        processA.saveA(subMessage[1], uuid);

        processSend.sendCom(uuid, subMessage[1], Long.valueOf(subMessage[4]), subMessage[3] , "A");
    }

}