package com.example.homework01.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


/**
 * Created by Chershembeev_AE
 * Date: 09.07.2019
 * Time: 17:08.
 */
@Component
public class MessageImpl implements Message {
    private static final Logger LOGGER = Logger.getLogger(MessageImpl.class.getName());



    private BufferedReader reader;

    @Override
    public String askQuestion(String question) {
        if (reader == null) {
            reader = new BufferedReader(new InputStreamReader(System.in));
            LOGGER.info("Reader initiated");
        }

        String answer = "";
        try {
            System.out.println(question);
            answer = reader.readLine();
        } catch (IOException e) {
            LOGGER.error("Error while reading answer", e);
        }
        return answer;
    }

    @Override
    public void close() {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                LOGGER.error("Reader was not closed properly", e);
            }
            LOGGER.info("Reader closed");
        }
    }
}
