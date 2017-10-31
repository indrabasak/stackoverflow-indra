package com.basaki.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MyClass.class, LearnerFactory.class})
public class MyClassPowerMockitoWhenNewTest {

    @Test
    public void test() throws Exception {
        StaticLearner mockLearner = PowerMockito.mock(StaticLearner.class);
        when(mockLearner.doSomething(anyString())).thenReturn("dummy");

        PowerMockito.whenNew(StaticLearner.class)
                .withNoArguments()
                .thenReturn(mockLearner);

        MyClass myClass = new MyClass(true, StaticLearner.class);
        myClass.process();

        myClass.doSomething();
    }

    @Test
    public void test2() throws InstantiationException, IllegalAccessException {
        mockStatic(LearnerFactory.class);
        StaticLearner mockLearner = PowerMockito.mock(StaticLearner.class);
        when(mockLearner.doSomething(anyString())).thenReturn("dummy");

        when(LearnerFactory.getInstance(eq(StaticLearner.class)))
                .thenReturn(mockLearner);

        MyClass myClass = new MyClass(true, StaticLearner.class);
        myClass.process();

        myClass.doSomething();
    }
}
