package com.digisky.liuwei2.tinyexample.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

/**
 * @author liuwei2
 * 2020/4/3 10:35
 */
public class TestCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        supplyAsync();

        final CompletableFuture<Integer> future = compute();

        class Client extends Thread {
            CompletableFuture<Integer> f;

            public Client(@NotNull String name, CompletableFuture<Integer> f) {
                super(name);
                this.f = f;
            }

            @Override
            public void run() {
                try {
                    System.out.println(this.getName() + ": " + f.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        new Client().start();
    }

    private static void supplyAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        int a = future.join();
        System.out.println("a: "+ a);
        int b = future.get();
        System.out.println("b: "+ b);
    }

    public static CompletableFuture<Integer> compute() {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        return future;
    }
}
