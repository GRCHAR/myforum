package com.example.forum;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLSyntaxErrorException;

@SpringBootTest
class ForumApplicationTests {

    @Test
    void contextLoads() {
        String test = System.getProperty("java.version");
        System.out.println(test);
        char one = test.charAt(0);
        char tow = test.charAt(2);
        System.out.println(one);
        System.out.println(tow);
        if(test.length() >= 5){
            System.out.println(test.charAt(4));
        }

    }

}
