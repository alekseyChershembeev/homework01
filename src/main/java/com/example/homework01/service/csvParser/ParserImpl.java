package com.example.homework01.service.csvParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * Created by Chershembeev_AE
 * Date: 09.07.2019
 * Time: 17:05.
 */


public class ParserImpl implements Parser {
    private static final Logger LOGGER = Logger.getLogger(ParserImpl.class.getName());


    @Override
    public Map<String, String> getAllQuestions(String fileName) {

        Map<String, String> questions = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while (reader.ready()) {
                String line;
                line = reader.readLine();
                String[] arr = line.split(",");
                questions.put(arr[0], arr[1]);

            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage() + " " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }
}
