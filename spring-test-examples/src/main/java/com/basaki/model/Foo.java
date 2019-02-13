package com.basaki.model;

public class Foo {

    public Bar doSomething(String name) {
        Bar bar = new Bar();
        bar.setName(name);

        return bar;
    }

    public String saySayonara(String name) {
        return "Sayonara " + name;
    }

    public String saySayonaraX() {
        return "Sayonara Joe!";
    }
}
