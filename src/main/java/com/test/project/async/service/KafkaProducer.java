package com.test.project.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic , String key, String date, String uuid, String message, Long nextSeq) {
        log.info("[Test] Kafka Producer message : {}{}{}{}{}", key, date, uuid, message,nextSeq);
        this.kafkaTemplate.send(topic, key + "/" + date + "/" + uuid + "/" + message + "/" + nextSeq.toString());
    }
}