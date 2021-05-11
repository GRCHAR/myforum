package com.example.forum.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @author genghaoran
 */
@Configuration
public class RabbitmqConfig {
    @Bean
    public Queue queue(){
        return new Queue("Queue1");
    }
}
