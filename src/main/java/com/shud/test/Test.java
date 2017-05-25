package com.shud.test;

import com.shud.model.User;
import com.shud.service.UserService;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Scanner;


/**
 * Created by shud on 2017/5/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class Test {

    Logger logger = Logger.getLogger(Test.class);
    @Resource
    private UserService userService;

    @org.junit.Test
    public void testSomething(){
        logger.info("test");
        User user = userService.getUser(34);
        logger.info(user.toString());

    }

    public static void main(String[] args){
        new ClassPathXmlApplicationContext("spring/applicationContext-quartz.xml");
    }

}
