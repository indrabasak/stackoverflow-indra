package com.basaki.aspect;

import com.basaki.annotation.NewLogArguments;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class NewLogArgumentsAspect {

    @Around("@annotation(annotation) || @within(annotation)")
    public Object logArguments(ProceedingJoinPoint joinPoint,
            NewLogArguments annotation) throws Throwable {
        log.info("Inside NewLogArgumentsAspect.logArguments()");

        return joinPoint.proceed();
    }
}