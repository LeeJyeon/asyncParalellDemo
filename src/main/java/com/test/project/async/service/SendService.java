package com.test.project.async.service;

import com.test.project.async.repository.ReceiveRepository;
import com.test.project.async.repository.SendRepository;
import com.test.project.async.table.Send;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendService {

    @Autowired
    SendRepository sendRepository;

    public void sendCom(String date , Long comNumber , String context) {
        Send data = new Send();
        data.setDate(date);
        data.setComNumber(comNumber);
        data.setContent( "done{" + context + "}");
        sendRepository.save(data);
    }
}
