package com.basaki.aspect;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * {@code MethodArgValueAspect} intercepts when any method in class {@class
 * BookController} is invoked.
 * <p>
 *
 * @author Indra Basak
 * @since 10/11/17
 */
@Component
@Aspect
@Slf4j
public class MethodArgValueAspect {

    @Pointcut("execution(public * com.basaki.controller.BookController.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object advice(ProceedingJoinPoint pjp) throws Throwable {
        log.info(
                "Entering MethodArgValueAspect.advice() in class "
                        + pjp.getSignature().getDeclaringTypeName()
                        + " - method: " + pjp.getSignature().getName());

        if (pjp.getSignature() instanceof MethodSignature) {
            MethodSignature signature =
                    (MethodSignature) pjp.getSignature();
            Method method = signature.getMethod();

            String type = "";
            Object[] args = pjp.getArgs();
            for (Object arg : args) {
                Class clazz = arg.getClass();
                try {
                    Method mthd = arg.getClass().getMethod("getResponderType");
                    if (mthd != null) {
                        type = (String) clazz.getMethod(
                                "getResponderType").invoke(arg);
                        break;
                    }
                } catch (Exception e) {
                    //do nothing
                }
            }
        }

        return pjp.proceed();
    }
}
