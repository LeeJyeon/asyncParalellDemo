package com.test.project.async.service;

import com.test.project.async.repository.ReceiveRepository;
import com.test.project.async.table.Receive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
public class StartService {

    private final ReceiveRepository receiveRepository;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public StartService(ReceiveRepository receiveRepository, KafkaProducer kafkaProducer) {
        this.receiveRepository = receiveRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public void receiveData(String com) {

        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedNow = now.format(formatter);

        UUID uuid;
        while (true) {
            uuid = UUID.randomUUID();
            if (uuid.toString().substring(0).compareTo("9") <= 0 &&
                    uuid.toString().substring(0).compareTo("0") >= 0) {
                break;
            }
            continue;
        }


        Long nextSeq = receiveRepository.selectMaxNumber(formattedNow);
        log.info("nextSeq {}", nextSeq);

        Receive data = new Receive();
        data.setContent(com);
        data.setDate(formattedNow);
        data.setComNumber(nextSeq);
        data.setUuid(uuid.toString());

        receiveRepository.save(data);
        kafkaProducer.sendMessage("testA", "A", formattedNow, uuid.toString(), com, nextSeq);
        kafkaProducer.sendMessage("testB", "B", formattedNow, uuid.toString(), com, nextSeq);
        kafkaProducer.sendMessage("testC", "C", formattedNow, uuid.toString(), com, nextSeq);

        log.info("[JOB] receive success & publish");

    }


}


