package com.example.homework01_4.demo.service.tester;

import com.example.homework01_4.demo.controller.Message;
import com.example.homework01_4.demo.service.csvParser.Parser;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Chershembeev_AE
 * Date: 09.07.2019
 * Time: 17:06.
 */

//{
//  "properties": [
//    {
//      "name": "language",
//      "type": "java.lang.String",
//      "description": "Description for language."
//    },
//
//    {
//      "name": "file.name.en",
//      "type": "java.lang.String",
//      "description": "Description for file.name.en."
//    },
//    {
//      "name": "file.name.ru",
//      "type": "java.lang.String",
//      "description": "Description for file.name.ru."
//    }
//  ] }

@Service
public class TestImpl implements Test {
    private static final Logger LOGGER = Logger.getLogger(TestImpl.class.getName());

    private final Message message;
    private final Parser parser;
    private Locale locale;
    private final MessageSource messageSource;

    @Value("${file.name.en}")
    private String fileNameEn;
    @Value("${file.name.ru}")
    private String fileNameRu;
    @Value("${language}")
    private String language;

    @Autowired
    public TestImpl(Message message, Parser parser, MessageSource messageSource) {
        this.message = message;
        this.parser = parser;
        this.messageSource = messageSource;
    }

//    @PostConstruct
//    private void startApp(){
//        getTest();
//    }
    @Override
    public void getTest() {

        if (locale == null) {
            defineLocale();
        }
        String fileName = locale == Locale.ENGLISH ? fileNameEn : fileNameRu;
        Map<String, String> questions = parser.getAllQuestions(fileName);

        String name;
        String surName;

        name = message.askQuestion(messageSource.getMessage("enter.first.name", new Object[]{}, locale));

        surName = message.askQuestion(messageSource.getMessage("enter.second.name", new Object[]{}, locale));


        AtomicInteger count = new AtomicInteger();
        questions.forEach((k, v) -> {

            String answer =message.askQuestion(k);

            if (answer.equals(v))
                count.getAndIncrement();
        });

        System.out.println(name + " " + surName);
        message.askQuestion(messageSource.getMessage("correct.answers", new Object[]{count}, locale));



    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    private void defineLocale() {
        if (language == null || language.isEmpty()) {
            locale = LocaleContextHolder.getLocale();
        } else if (language.equals("en")) {
            locale = Locale.ENGLISH;
        } else {
            locale = new Locale("ru");
        }
    }

    @Override
    public void close() {
        message.close();
    }
}
