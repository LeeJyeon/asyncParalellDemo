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

        Sleep sleep = new Sleep();
        sleep.sleep("C", uuid);

        checkCRepository.save(data);

        return true;
    }
}
