package com.example.forum.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author genghaoran
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final Logger logger = LoggerFactory.getLogger(Sender.class);

    public void send(String context){
        this.amqpTemplate.convertAndSend("Queue1", context);
    }



}
