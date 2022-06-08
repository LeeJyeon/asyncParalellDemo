package com.test.project.async.kafka;

import com.test.project.async.service.ProcessC;
import com.test.project.async.service.ProcessSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerC {

    private final ProcessC processC;
    private final ProcessSend processSend;


    public KafkaConsumerC(ProcessC processC, ProcessSend processSend) {
        this.processC = processC;
        this.processSend = processSend;
    }


    @KafkaListener(topics = {"testC"} , errorHandler = "kafkaExceptionHandler")
    public void receiveMessageC(String message) throws InterruptedException {
        log.info("[Test] Kafka Consumer message : {}", message);
        // key, date, uuid, message, nextSeq

        String[] subMessage = message.split("/");
        String uuid = subMessage[2];

        processC.saveC(subMessage[1], uuid);

        processSend.sendCom(uuid, subMessage[1], Long.valueOf(subMessage[4]), subMessage[3] , "C");


    }

}