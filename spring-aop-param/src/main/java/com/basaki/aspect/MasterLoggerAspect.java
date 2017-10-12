package com.basaki.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * {@code MasterLoggerAspect} intercepts any method in classes under package
 * com.basaki including sub-packages before its execution abd logs a message.
 * <p>
 *
 * @author Indra Basak
 * @since 10/11/17
 */
@Component
@Aspect
public class MasterLoggerAspect {

    //@Pointcut("execution(* com.basaki..*.*(..))")
    public void logForAllMethods() {
    }

    @Before("execution(* com.basaki.controller.*.*(..) )")
    public void doForEveryServicesMethod(JoinPoint jp) {
        System.out.println(
                "hurray! In class: " + jp.getSignature().getDeclaringTypeName() + " - before method: " + jp.getSignature().getName());
    }

    @Before("execution(* com.basaki..*.*(..) )")
    public void doForEveryMainClassMethod(JoinPoint jp) {
        System.out.println(
                "hurray! In class: " + jp.getClass() + " - before method: " + jp.getSignature().getName());
    }
}
