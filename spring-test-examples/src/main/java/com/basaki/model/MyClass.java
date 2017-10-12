package com.basaki.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyClass {

    private boolean historic;

    private Learner learner;

    public MyClass(boolean historic,
            Class<? extends Learner> learner) throws IllegalAccessException, InstantiationException {
        System.out.println("****** " + learner);
        //this.learner = learner.newInstance();
        this.learner = LearnerFactory.getInstance(learner);
        this.historic = historic;
    }

    public void process() {
        System.out.println(learner.doSomething("hello"));
    }

    public void doSomething() {
        StaticLearner l = new StaticLearner();
        System.out.println(l.doSomething("888 "));
    }
}
