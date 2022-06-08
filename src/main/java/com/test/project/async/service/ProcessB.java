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

        Sleep sleep = new Sleep();
        sleep.sleep("B", uuid);
        checkBRepository.save(data);

        return true;
    }


}
