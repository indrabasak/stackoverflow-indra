package com.basaki.aspect;

import com.basaki.Application;
import com.basaki.config.SpringConfiguration;
import com.basaki.model.ClassA;
import com.basaki.model.extra.ClassB;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * {@code ExampleAspectTest} is a sample unit test for {@class ExampleAspect}
 * aspect.
 * <p>
 *
 * @author Indra Basak
 * @since 11/9/17
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({LoggerFactory.class, LogFactory.class})
public class ExampleAspectTest {

    @Mock
    private Logger loggerMock;

    @Mock
    private Log logger;

    private ClassA proxy;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(LoggerFactory.class);
        when(LoggerFactory.getLogger(ExampleAspect.class)).
                thenReturn(loggerMock);
        when(loggerMock.isDebugEnabled()).thenReturn(true);

        PowerMockito.mockStatic(LogFactory.class);
        when(LogFactory.getLog(ExampleAspect.class)).
                thenReturn(logger);
        when(LogFactory.getLog(any(Class.class))).
                thenReturn(logger);
        when(logger.isDebugEnabled()).thenReturn(true);


        MockitoAnnotations.initMocks(this);
        ClassA target = new ClassA();
        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        ExampleAspect aspect = new ExampleAspect();

        factory.addAspect(aspect);
        proxy = factory.getProxy();
    }

    @Test
    public void testClassAMethodA() {
        proxy.methodA();
        //doesn't work
        //verify(loggerMock).info(anyString());
    }

    @Test
    public void testClassBMethodB() {
        ClassB target = new ClassB();
        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        ExampleAspect aspect = new ExampleAspect();

        factory.addAspect(aspect);
        ClassB proxy3 = factory.getProxy();
        proxy3.methodB();
    }

    @Test
    public void testSpringConfiguration() {
        SpringConfiguration target = new SpringConfiguration();
        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        ExampleAspect aspect = new ExampleAspect();

        factory.addAspect(aspect);
        SpringConfiguration proxy1 = factory.getProxy();
        proxy1.getHttpMessageConverter();
    }

    @Test
    public void testApplication() {
        Application target = new Application();
        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        ExampleAspect aspect = new ExampleAspect();

        factory.addAspect(aspect);
        Application proxy2 = factory.getProxy();
        proxy2.sayHello();
    }
}