package com.test.project.async.service;

import com.test.project.async.redis.UseRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ProcessSend {

    private final SendService sendService;
    private final UseRedis useRedis;

    public ProcessSend(SendService sendService, UseRedis useRedis) {
        this.sendService = sendService;
        this.useRedis = useRedis;
    }

    public void sendCom(String uuid, String date, Long comNumber, String context, String dest) {

        useRedis.setValue(dest, uuid);

        if (dest.equals("D")) {
            sendService.sendCom(date, comNumber, "[Time Out]" + context);
        } else {
            if (useRedis.checkNextAble(uuid) && useRedis.getValue(uuid, "D") == null ) {
                sendService.sendCom(date,
                        comNumber,
                        context + " / " + dest +" / "
                                + useRedis.getValue(uuid, "A")
                                + useRedis.getValue(uuid, "B")
                                + useRedis.getValue(uuid, "C")
                );
            }
        }

    }
}
