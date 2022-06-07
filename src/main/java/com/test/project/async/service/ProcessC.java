package com.test.project.async.service;

import com.test.project.async.repository.CheckCRepository;
import com.test.project.async.table.CheckC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessC {

    @Autowired
    CheckCRepository checkCRepository;

    public boolean saveC(String date, String uuid) throws InterruptedException {

        CheckC data = new CheckC();
        data.setDate(date);
        data.setUuid(uuid);

//        if (uuid.substring(0).compareTo("3") <= 0) {
//            Thread.sleep(5000L);
//        } else if (uuid.substring(0).compareTo("6") <= 0) {
//            Thread.sleep(1000L);
//        } else {
//            Thread.sleep(3000L);
//        }

        checkCRepository.save(data);

        return true;
    }
}
