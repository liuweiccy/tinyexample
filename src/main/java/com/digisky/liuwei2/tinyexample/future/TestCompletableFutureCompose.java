package com.digisky.liuwei2.tinyexample.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author liuwei2
 * 2020/4/9 17:57
 */
public class TestCompletableFutureCompose {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()-> {
            System.out.println(Runtime.getRuntime().availableProcessors());
            return 10;
        });

        CompletableFuture<Integer> future1 = future.thenComposeAsync(v-> CompletableFuture.supplyAsync(()-> v * 10));
        // System.out.println(future1.get());


        CompletableFuture<Integer> future2 = future.thenCombineAsync(future1, Integer::sum);
        System.out.println(future2.get());


        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            Random random = new Random();
            int res = random.nextInt(100) + 1;
            System.out.println("res3: " + res);
            return res;
        });

        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> {
            Random random = new Random();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int res = random.nextInt(100) + 1;
            System.out.println("res4: " + res);
            return res;
        });

        CompletableFuture.anyOf(future3, future4).thenAccept(System.out::println);
        CompletableFuture.allOf(future3, future4).thenRun(()-> System.out.println("完成执行"));
        TimeUnit.SECONDS.sleep(3);
    }
}
