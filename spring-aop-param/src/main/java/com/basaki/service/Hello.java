package com.basaki.service;

import org.springframework.stereotype.Component;

@Component
public class Hello {

    public String getGreetingA() {
        return "hello";
    }
    public boolean getGreetingB() {
        return false;
    }
}
