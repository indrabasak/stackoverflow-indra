package com.basaki.model;

public class StaticLearner implements Learner {

    @Override
    public String doSomething(String arg) {
        return "doing something " + arg;
    }
}
