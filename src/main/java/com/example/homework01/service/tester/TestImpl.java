package com.example.homework01.service.tester;

import com.example.homework01.controller.Message;
import com.example.homework01.service.csvParser.Parser;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
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

@Service
public class TestImpl implements Test {
    private static final Logger LOGGER = Logger.getLogger(TestImpl.class.getName());

    private Message message;
    private Parser parser;
    private Locale locale;
    private MessageSource messageSource;

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
