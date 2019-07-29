package com.example.homework01.logging;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Chershembeev_AE
 * Date: 24.07.2019
 * Time: 17:53.
 */

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    @Before("within(com.example.homework01..*)")
    public void logBefore(JoinPoint joinPoint){
        LOGGER.info("Вызов метода : "+ joinPoint.getSignature().getName());
    }
}
