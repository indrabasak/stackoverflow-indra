package com.basaki.misc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PerformLongRunningJob {

    public static void main(String... args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() ->
                LongRunningJob.execute("John"), executor);

        try {
            String result = future.get(4, TimeUnit.SECONDS);
            System.out.println("*** result: " + result);
        } catch (Exception e) {
            System.out.println("*** encountered exception");
            e.printStackTrace();
        }

        System.out.println("--------------------");
        executor.shutdown();
    }
}
