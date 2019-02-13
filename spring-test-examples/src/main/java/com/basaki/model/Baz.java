package com.basaki.model;

public class Baz {

    public Bar doSomething(String name) {
        Foo foo = new Foo();
        return foo.doSomething(name);
    }
}
