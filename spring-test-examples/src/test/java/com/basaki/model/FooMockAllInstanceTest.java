package com.basaki.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Foo.class)
public class FooMockAllInstanceTest {

    @Test
    public void testMockInstanceofObjectCreation() throws Exception {
        Bar mockBar = PowerMockito.mock(Bar.class);
        when(mockBar.sayHello()).thenReturn("Hi John!");
        PowerMockito.whenNew(Bar.class)
                .withNoArguments()
                .thenReturn(mockBar);

        Foo myFooOne = new Foo();
        assertEquals(mockBar, myFooOne.doSomething("Jane"));

        Foo myFooTwo = new Foo();
        assertEquals(mockBar, myFooTwo.doSomething("Sarah"));

        Baz bazOne = new Baz();
        assertEquals(mockBar, bazOne.doSomething("Sam"));

        Baz bazTwo = new Baz();
        assertEquals(mockBar, bazTwo.doSomething("Nina"));
    }

    @Test
    public void test() throws Exception {
        PowerMockito.stub(PowerMockito.method(Foo.class, "saySayonara",
                String.class)).toReturn("Goodbye John!");

        Foo myFooOne = new Foo();
        assertEquals("Goodbye John!", myFooOne.saySayonara("hh"));

        Foo myFooTwo = new Foo();
        assertEquals("Goodbye John!", myFooTwo.saySayonara("kk"));
    }

    @Test
    public void testStubbingMethod() throws Exception {
        Bar mockBar = PowerMockito.mock(Bar.class);
        when(mockBar.sayHello()).thenReturn("Hi John!");

        PowerMockito.stub(PowerMockito.method(Foo.class, "doSomething",
                String.class)).toReturn(mockBar);

        Foo myFooOne = new Foo();
        assertEquals(mockBar, myFooOne.doSomething("Jane"));

        Foo myFooTwo = new Foo();
        assertEquals(mockBar, myFooTwo.doSomething("Sarah"));

        Baz bazOne = new Baz();
        assertEquals(mockBar, bazOne.doSomething("Sam"));

        Baz bazTwo = new Baz();
        assertEquals(mockBar, bazTwo.doSomething("Nina"));
    }
}
