package com.example.forum.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @author genghaoran
 */
@Component
@RabbitListener(queues = "upload.video")
public class Receiver {

    private final Logger logger = LoggerFactory.getLogger(Receiver.class);
    @RabbitHandler
    public void queueReceiver(JSONObject queue1){
        logger.info("Receive A:" + queue1.toString());
    }

}
