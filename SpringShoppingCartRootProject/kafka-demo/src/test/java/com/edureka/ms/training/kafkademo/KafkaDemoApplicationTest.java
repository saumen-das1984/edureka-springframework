package com.edureka.ms.training.kafkademo;

import com.edureka.ms.training.kafkademo.consumer.Reciever;
import com.edureka.ms.training.kafkademo.producer.Sender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KafkaDemoApplicationTest {


    @Autowired private Reciever reciever;

    @Autowired private Sender sender;

    @Test
    public void testMessageRecieved() throws InterruptedException {
        sender.send("Hello This is a first message");
        CountDownLatch countDownLatch = reciever.getCountDownLatch();
        countDownLatch.await(10000, TimeUnit.MILLISECONDS);
        Assertions.assertThat(countDownLatch.getCount()).isEqualTo(0);

    }



}