package com.test.project.async.service;

import com.test.project.async.repository.CheckARepository;
import com.test.project.async.table.CheckA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessA {

    @Autowired
    CheckARepository checkARepository;

    public boolean saveA(String date, String uuid) throws InterruptedException {

        CheckA data = new CheckA();
        data.setDate(date);
        data.setUuid(uuid);
//
//        if (uuid.substring(0).compareTo("3") <= 0) {
//            Thread.sleep(1000L);
//        } else if (uuid.substring(0).compareTo("6") <= 0) {
//            Thread.sleep(3000L);
//        } else {
//            Thread.sleep(5000L);
//        }

        checkARepository.save(data);

        return true;

    }
}
