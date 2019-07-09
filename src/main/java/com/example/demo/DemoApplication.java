package com.example.demo;

import com.example.demo.service.tester.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DemoApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        Test test = (Test) context.getBean("tester");
        test.getTest();
    }

}
