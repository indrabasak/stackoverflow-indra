package com.basaki.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * {@code ExampleAspect} is a simple aspect to demonstrate unit testing.
 * <p>
 *
 * @author Indra Basak
 * @since 11/9/17
 */
@Aspect
@Slf4j
public class ExampleAspect {

    @Around("execution(* com.*.*.*.*(..))")
    public void printResult(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before Method Execution");
        log.info("Before Method Execution");
        joinPoint.proceed();
        System.out.println("After Method Execution");
        log.info("After Method Execution");
    }
}
