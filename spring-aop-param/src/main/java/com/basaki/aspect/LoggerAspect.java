package com.basaki.aspect;

import com.basaki.annotation.EnableHttpLogging;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * {@code LoggerAspect} intercepts any method tagged with {@code
 * com.basaki.annotation.EnableHttpLogging}. It is an example of {@code Around}
 * aspect.
 * <p>
 *
 * @author Indra Basak
 * @since 10/11/17
 */
@Aspect
@Component
@Slf4j
public class LoggerAspect {

    @Around("execution(* *(..)) && @annotation(annotation)")
    public Object handleController(
            final ProceedingJoinPoint proceedingJoinPoint,
            EnableHttpLogging annotation)
            throws Throwable {

        log.info("controller....before ");
        return proceedingJoinPoint.proceed();

    }
}
