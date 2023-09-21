package com.example.forum;

import com.example.forum.queue.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLSyntaxErrorException;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ForumApplicationTests {

    @Autowired
    private Sender sender;

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

    @Test
    public void QueueSend(){
        int i = 2;
        for(int j = 0;j < i;j++){
            String msg = "Queue1 msg" + j + new Date();
            try{
                sender.send(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
