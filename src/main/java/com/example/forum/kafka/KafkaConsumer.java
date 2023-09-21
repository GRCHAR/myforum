package com.example.forum.kafka;

import com.alibaba.fastjson.JSON;
import com.example.forum.bo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author genghaoran
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "register")
    public void consume(String message) {
        log.info("接收到消息：" + message);
        User user = JSON.parseObject(message, User.class);
        log.info("确认用户信息接收：" + user.getName());
    }

}
