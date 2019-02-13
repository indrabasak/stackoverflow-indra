package com.basaki.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bar {
    private String name;

    public String sayHello() {
        return "hello " + name;
    }
}
