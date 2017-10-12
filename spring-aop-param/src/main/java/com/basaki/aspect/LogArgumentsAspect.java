package com.basaki.aspect;

import com.basaki.annotation.LogArguments;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * {@code LogArgumentsAspect} intercepts any method tagged with {@code
 * com.basaki.annotation.LogArguments}. It is an example of {@code Around}
 * aspect.
 * <p>
 *
 * @author Indra Basak
 * @since 10/11/17
 */
@Component
@Aspect
@Slf4j
public class LogArgumentsAspect {

    @Around("@annotation(annotation) || @within(annotation)")
    public Object logArguments(ProceedingJoinPoint joinPoint,
            LogArguments annotation) throws Throwable {
        log.info("Inside LogArgumentsAspect.logArguments()");
        //System.err.println("put breakpoint here, never stops here");

        return joinPoint.proceed();
    }
}
