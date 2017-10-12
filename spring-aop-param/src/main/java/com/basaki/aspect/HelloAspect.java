package com.basaki.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * {@code HelloAspect} intercepts all public methods in {@code
 * com.basaki.service.Hello} which as return type of {@code String}.
 * <p>
 *
 * @author Indra Basak
 * @since 10/12/17
 */
@Component
@Aspect
public class HelloAspect {

    @Pointcut("execution(public String com.basaki.service.Hello.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object advice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("---------- HelloAspect.advice() ");
        System.out.println(
                "In class: " + pjp.getSignature().getDeclaringTypeName() + " - method: " + pjp.getSignature().getName());
        // do something...
        Object result = pjp.proceed();
        // do something...
        return result;
    }
}
