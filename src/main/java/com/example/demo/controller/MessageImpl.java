package com.example.demo.controller;

import org.apache.log4j.Logger;


/**
 * Created by Chershembeev_AE
 * Date: 09.07.2019
 * Time: 17:08.
 */

public class MessageImpl implements Message {
    private static final Logger LOGGER = Logger.getLogger(MessageImpl.class.getName());

    @Override
    public String askQuestion(String question) {

        LOGGER.info("Return question: " + question);

        return question;
    }

}
