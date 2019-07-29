package com.example.homework01_4.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Created by Chershembeev_AE
 * Date: 11.07.2019
 * Time: 14:16.
 */

@Configuration
public class MessageSourceConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms =
                new ReloadableResourceBundleMessageSource();

        ms.setBasename("classpath:messages");
        ms.setDefaultEncoding("windows-1251");
        return ms;
    }

//    @Bean
//    Message message(){
//        return Mockito.mock(MessageImpl.class);
//    }
//
//    @Bean
//    Parser parser(){
//        return new ParserImpl();
//    }
//    @Bean
//    Test test(){
//        return  new TestImpl(message(),parser(),messageSource());
//    }


}
