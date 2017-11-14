package com.basaki.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * {@code ParamAspect} is an example of intercepting calls to parameterized
 * method of a generic class and further filtering based on concrete argument
 * type. The paramter information is lost drring erasure. Please see
 * https://www.eclipse.org/aspectj/doc/released/adk15notebook/generics-inAspectJ5.html.
 * <p>
 *
 * @author Indra Basak
 * @since 10/12/17
 */
@Component
@Aspect
public class ParamAspect {

    @Pointcut("execution(public * com.basaki.model.Param+.execute(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void intercept(JoinPoint jp) {
        System.out.println(
                "Entering class: " + jp.getSignature().getDeclaringTypeName() +
                        " - before method: " + jp.getSignature().getName());
        for (Object obj : jp.getArgs()) {
            System.out.println(obj);
        }

        Object[] args = jp.getArgs();
        if (args.length == 1) {
            if (args[0] instanceof String) {
                System.out.println("1. parameter type is string");
            } else {
                System.out.println("2. parameter type is not string");
            }
        }
    }
}
