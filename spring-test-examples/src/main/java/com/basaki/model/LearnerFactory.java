package com.basaki.model;

public class LearnerFactory {

    public static Learner getInstance(
            Class<? extends Learner> learner) throws IllegalAccessException, InstantiationException {

        return learner.newInstance();
    }
}
