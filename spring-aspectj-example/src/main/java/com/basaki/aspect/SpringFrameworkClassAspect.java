package com.basaki.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * {@code SpringFrameworkClassAspect} is an example of intercepting Spring
 * framework using AspectJ source weaving. This aspect, intercepts calls to
 * method {@code ignoreResourceType} of class {@code CommonAnnotationBeanPostProcessor}.
 * <p>
 *
 * @author Indra Basak
 * @since 11/11/17
 */
@Component
@Aspect
public class SpringFrameworkClassAspect {

    @Pointcut("execution(* org.springframework.context.annotation.CommonAnnotationBeanPostProcessor.ignoreResourceType(..))")
    public void intercept() {
    }

    @Around("intercept()")
    public Object adviceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("******************************" +
                "Entering SpringFrameworkClassAspect.adviceAround() in class "
                + joinPoint.getSignature().getDeclaringTypeName()
                + " - method: " + joinPoint.getSignature().getName());
        return joinPoint.getArgs();
    }
}
