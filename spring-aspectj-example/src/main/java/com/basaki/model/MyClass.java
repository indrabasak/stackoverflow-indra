package com.basaki.model;

import com.basaki.annotation.MyAnno;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * {@code MyClass} is an example on how to use {@code MyAnno} annotation.
 *
 * @author Indra Basak
 * @since 11/3/17
 */
@Component
public class MyClass {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void schedule() {
        myMethod("test");
    }

    @MyAnno(cl = MyClass.class, description = "desc")
    private void myMethod(String text) {
        System.out.println("****************** " + text);
    }
}
