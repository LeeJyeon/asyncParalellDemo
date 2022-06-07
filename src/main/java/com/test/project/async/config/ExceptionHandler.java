package com.test.project.async.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;

@Slf4j
@Configuration
@EnableKafka
public class ExceptionHandler {
    @Bean
    public KafkaListenerErrorHandler kafkaExceptionHandler() {
        log.error("jihyun error kafka!");
        return new KafkaListenerErrorHandler() {
            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
                return null;
            }
        };
    }
}
