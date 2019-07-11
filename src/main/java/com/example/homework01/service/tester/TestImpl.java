package com.example.homework01.service.tester;

import com.example.homework01.controller.Message;
import com.example.homework01.service.csvParser.Parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Chershembeev_AE
 * Date: 09.07.2019
 * Time: 17:06.
 */

@Service
public class TestImpl implements Test {
    private static final Logger LOGGER = Logger.getLogger(TestImpl.class.getName());

    private Message message;
    private Parser parser;

    @Autowired
    public TestImpl(Message message, Parser parser) {
        this.message = message;
        this.parser = parser;
    }


    @Override
    public void getTest() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String name = null;
        String surName =null;

        try {
            System.out.println( message.askQuestion("What is your name?"));
            name = reader.readLine();
            System.out.println( message.askQuestion("What is your surName?"));
            surName = reader.readLine();
        } catch (IOException e) {
            LOGGER.error(name + ""+ surName, e);
        }

        Map<String,String>questions = parser.getAllQuestions("C:\\Users\\Chershembeev_AE\\Downloads\\demo\\src\\main\\resources\\testing.csv");

        AtomicInteger count = new AtomicInteger();
        questions.forEach((k,v)->{
            System.out.println(message.askQuestion(k));
            String answer = "";
            try {
                answer = reader.readLine();
            } catch (IOException e) {
                LOGGER.warn(answer + " "+ e.getMessage());
            }
            if (answer.equals(v))
                count.getAndIncrement();
        });

        System.out.println(name + " "+ surName);
        System.out.println("Right answers: "+ count+"/"+5);



    }
}
