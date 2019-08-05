package com.example.homework01_4.demo.cli;

import com.example.homework01_4.demo.controller.Message;
import com.example.homework01_4.demo.service.csvParser.Parser;
import com.example.homework01_4.demo.service.tester.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import java.util.Locale;


/**
 * Created by Chershembeev_AE
 * Date: 05.08.2019
 * Time: 14:40.
 */
@ShellComponent
public class TesterCLI {

    private Test test;
    private Parser parser;
    private Message message;


    @Autowired
    public TesterCLI(Test test, Parser parser, Message message) {
        this.test = test;
        this.parser = parser;
        this.message = message;

    }

    @ShellMethod(value = "Start test", key = "start")
    public void startTesting() {
        test.getTest();
    }

    @ShellMethod(value = "Choose language : english (eng) or russian (ru)",
            key = "language")
    public void changeLanguage(@ShellOption(help = "language") String language) {
        if (language.equals("eng"))
            test.setLocale(Locale.ENGLISH);
        else if (language.equals("ru"))
            test.setLocale(new Locale("ru"));
        else message.message("Error with language: only (eng) or (ru)");
    }



}
