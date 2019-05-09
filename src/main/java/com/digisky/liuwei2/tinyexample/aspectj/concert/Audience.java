package com.digisky.liuwei2.tinyexample.aspectj.concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liuwei2
 */
@Aspect
@Slf4j
public class Audience {

    @Pointcut("execution(* com.digisky.liuwei2.tinyexample.aspectj.concert.Performance.perform(..))")
    public void perform() {}

    @Around("perform()")
    public void watchPerformance(ProceedingJoinPoint jp) {
        try {
            log.info("Silencing cell phones");
            log.info("Taking seats");
            jp.proceed();
            log.info("CLAP CLAP CLAP!");
        } catch (Throwable throwable) {
            log.info("Demanding a refund");
        }
    }
}

