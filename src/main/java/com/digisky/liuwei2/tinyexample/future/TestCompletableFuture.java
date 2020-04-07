package com.digisky.liuwei2.tinyexample.future;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

/**
 * @author liuwei2
 * 2020/4/3 10:35
 */
public class TestCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        // supplyAsync();

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

        new Client("线程一", future).start();
        new Client("线程二", future).start();
        System.out.println("waiting");
        // future.complete(100);
        future.completeExceptionally(new Exception("未完成异常"));
        TimeUnit.SECONDS.sleep(1);
        future.obtrudeException(new Exception("改变未完成的异常"));
        System.in.read();
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
