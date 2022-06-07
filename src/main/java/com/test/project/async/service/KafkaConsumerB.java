package com.test.project.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerB {

    private final ProcessB processB;
    private final ProcessSend processSend;


    public KafkaConsumerB(ProcessB processB, ProcessSend processSend) {
        this.processB = processB;
        this.processSend = processSend;
    }


    @KafkaListener(topics = {"testB"})
    public void receiveMessageB(String message) throws InterruptedException {
        log.info("[Test] Kafka Consumer message : {}", message);
        // key, date, uuid, message, nextSeq

        String[] subMessage = message.split("/");
        String uuid = subMessage[2];

        processB.saveB(subMessage[1], uuid);

        processSend.sendCom(uuid, subMessage[1], Long.valueOf(subMessage[4]), subMessage[3] , "B");


    }

}