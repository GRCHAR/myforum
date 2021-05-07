package com.example.forum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author genghaoran
 */
@SpringBootApplication(scanBasePackages={"com.example.forum.*"})
@MapperScan("com.example.forum.dao")
@ComponentScan(basePackages = {"com.example"})
@EnableCaching
@EnableRedisHttpSession
@Configuration
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

}
