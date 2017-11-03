package com.basaki.aspect;

import com.basaki.annotation.MyAnno;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * {@code MyAspect} intercepts any method execution if a
 * method is tagged with {@code com.basaki.annotation.MyAnno}
 * annotation.
 * <p>
 *
 * @author Indra Basak
 * @since 11/3/17
 */
@Component
@Aspect
@Slf4j
public class MyAspect {

    @Before("@annotation(myAnnoAnnotation)")
    public void myAnnoAspect(JoinPoint jp, MyAnno myAnnoAnnotation) {
        log.info(
                "Entering MyAspect.myAnnoAspect() in class "
                        + jp.getSignature().getDeclaringTypeName()
                        + " - method: " + jp.getSignature().getName());
    }

    @Before("@annotation(myAnnoAnnotation) || @within(myAnnoAnnotation)")
    public void myAnnoAspect2(JoinPoint jp, MyAnno myAnnoAnnotation) {
        log.info(
                "Entering MyAspect.myAnnoAspect2() in class "
                        + jp.getSignature().getDeclaringTypeName()
                        + " - method: " + jp.getSignature().getName());
    }
}
