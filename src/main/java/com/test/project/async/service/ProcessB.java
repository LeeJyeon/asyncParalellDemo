package com.test.project.async.service;

import com.test.project.async.repository.CheckBRepository;
import com.test.project.async.table.CheckB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessB {

    @Autowired
    CheckBRepository checkBRepository;

    public boolean saveB(String date, String uuid) throws InterruptedException {

        CheckB data = new CheckB();
        data.setDate(date);
        data.setUuid(uuid);
//
//        if (uuid.substring(0).compareTo("3") <= 0) {
//            Thread.sleep(3000L);
//        } else if (uuid.substring(0).compareTo("6") <= 0) {
//            Thread.sleep(5000L);
//        } else {
//            Thread.sleep(1000L);
//        }

        checkBRepository.save(data);

        return true;
    }
}
