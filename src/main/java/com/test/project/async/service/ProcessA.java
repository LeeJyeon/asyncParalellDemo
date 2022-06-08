package com.test.project.async.service;

import com.test.project.async.repository.CheckARepository;
import com.test.project.async.table.CheckA;
import io.netty.util.internal.logging.Slf4JLoggerFactory;
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

        Sleep sleep = new Sleep();
        sleep.sleep("A", uuid);

        checkARepository.save(data);

        return true;

    }

}
