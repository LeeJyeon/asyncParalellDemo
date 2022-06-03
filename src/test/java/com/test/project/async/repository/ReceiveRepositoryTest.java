package com.test.project.async.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@Slf4j
class ReceiveRepositoryTest {

    @Autowired
    ReceiveRepository receiveRepository;

    @Test
    void makeSequenceTest(){

        Long tmpLong = receiveRepository.selectMaxNumber("20220601");
        System.out.println(tmpLong);
        Assertions.assertThat(tmpLong).isEqualTo(1);

    }

}