package com.basaki.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * {@code SpringFrameworkClassAspect} is an example to illustrate that you
 * cannot
 * intercepting Spring framework classes using Spring AOP. This aspect,  fails
 * to intercepts calls to method {@code ignoreResourceType} of class {@code
 * CommonAnnotationBeanPostProcessor}.
 * <p>
 *
 * @author Indra Basak
 * @since 11/11/17
 */
@Component
@Aspect
@Slf4j
public class SpringFrameworkClassAspect {

    @Pointcut("execution(* org.springframework.context.annotation.CommonAnnotationBeanPostProcessor.ignoreResourceType(..))")
    public void intercept() {
    }

    @Around("intercept()")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.getArgs();
    }
}
