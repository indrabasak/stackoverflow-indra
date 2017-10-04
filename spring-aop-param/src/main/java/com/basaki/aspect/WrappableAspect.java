package com.basaki.aspect;

import com.basaki.annotation.Key;
import com.basaki.annotation.Wrappable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * {@code WrappableAspect} intercepts any method execution if a
 * method is tagged with {@code com.basaki.annotation.Wrappable}
 * annotation.
 * <p>
 *
 * @author Indra Basak
 * @since 10/4/17
 */
@Aspect
@Component
public class WrappableAspect {

    @After("@annotation(annotation) || @within(annotation)")
    public void wrapper(
            final JoinPoint pointcut,
            final Wrappable annotation) {
        Wrappable anno = annotation;
        List<Parameter> keyParams = new ArrayList<>();

        if (annotation == null) {
            if (pointcut.getSignature() instanceof MethodSignature) {
                MethodSignature signature =
                        (MethodSignature) pointcut.getSignature();
                Method method = signature.getMethod();
                anno = method.getAnnotation(Wrappable.class);

                Parameter[] params = method.getParameters();
                for (Parameter param : params) {
                    try {
                        Annotation keyAnno = param.getAnnotation(Key.class);
                        keyParams.add(param);
                    } catch (Exception e) {
                        //do nothing
                    }
                }
            }
        }
    }
}
