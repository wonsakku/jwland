package com.jwland.jwland.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Slf4j
@Component
public class LoggerAop {

    @Before("execution(* com.jwland.jwland..*Controller.*(..))")
    public void controllerLogger(JoinPoint joinPoint){
        log.info("Executed controller : {}", joinPoint.getSignature());
    }


}
