package com.basaki.aspect;

import com.basaki.annotation.MyAnno;
import java.lang.annotation.Annotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;

/**
 * {@code MyAspectTest} is a test class for {@code com.basaki.aspect.MyAspect}.
 * <p>
 *
 * @author Indra Basak
 * @since 11/7/17
 */
public class MyAspectTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Signature signature;

    @Mock
    private ProceedingJoinPoint pointcut;

    private MyAspect aspect;


    private Class clazz = String.class;

    private String methodName = "length";

    @Before
    public void setUp() {
        aspect = new MyAspect();
    }

    @Test
    public void testGenerateMetric() throws Throwable {
        when(signature.getDeclaringType()).thenReturn(clazz);
        when(signature.getName()).thenReturn(methodName);
        when(pointcut.getSignature()).thenReturn(signature);

        MyAnno annotation = MyAnnoAnnotation.getInstance(clazz);
        aspect.myAnnoAspect(pointcut, annotation);
    }

    public static class MyAnnoAnnotation {
        public static MyAnno getInstance(final Class clazz) {
            MyAnno instance = new MyAnno() {

                @Override
                public Class cl() {
                    return clazz;
                }

                @Override
                public String description() {
                    return "The class name is " + clazz.getSimpleName();
                }

                @Override
                public Class<? extends Annotation> annotationType() {
                    return null;
                }
            };

            return instance;
        }
    }
}
