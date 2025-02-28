package com.epam.rd.autocode.assessment.appliances.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingServices {

    private static final Logger logger = LoggerFactory.getLogger(LoggingServices.class);

    @After("@annotation(com.epam.rd.autocode.assessment.appliances.aspect.Loggable)")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        logger.info("Method {} was called with arguments: {}", methodName, methodArgs);
    }
}