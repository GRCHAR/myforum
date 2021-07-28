package com.example.forum.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author genghaoran
 */
@Component
@RabbitListener(queues = "test")
public class TestReceiver {

    private final Logger logger = LoggerFactory.getLogger(TestReceiver.class);

    @RabbitHandler
    public void receiverTest(String test){
        logger.info(test);
    }

}
