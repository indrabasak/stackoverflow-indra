package com.basaki.service;

import com.basaki.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SayHelloService {

    @Autowired
    private Application app;

    public String message(String name) {
        System.out.println("Hello Dear User - " + name );
        app.sayHello();
        return "Hello Dear User - " + name ;
    }

    public static String justAnotherStaticMethod() {
        System.out.println("-------- justAnotherStaticMethod");
        return "just stattic";
    }
}
