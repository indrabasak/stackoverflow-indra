package com.basaki.misc.future;

public class LongRunningJob {

    public static String execute(String name) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "hello " + name;

    }
}
