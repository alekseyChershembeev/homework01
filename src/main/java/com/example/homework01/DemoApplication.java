package com.example.homework01;

import com.example.homework01.controller.Message;
import com.example.homework01.controller.MessageImpl;
import com.example.homework01.service.csvParser.Parser;
import com.example.homework01.service.csvParser.ParserImpl;
import com.example.homework01.service.tester.Test;
import com.example.homework01.service.tester.TestImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class DemoApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoApplication.class);

        Test test =  context.getBean(TestImpl.class);
        test.getTest();

//        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
//        Test test = (Test) context.getBean("tester");
//        test.getTest();
    }

    @Bean
    Message messenger(){
        return new MessageImpl();
    }
    @Bean
    Parser parser(){
        return new ParserImpl();
    }



}
