package com.digisky.liuwei2.tinyexample.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author liuwei2
 * 2020/4/9 15:52
 */
public class TestCompletableFutureAction {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->10).thenAcceptAsync(v -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(v);
        });
        TimeUnit.SECONDS.sleep(2);
        System.out.println("输出生成的值：");
        System.out.println(future.get());

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
            Random random = new Random();
            return random.nextInt(100) + 1;
        });
        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(()->100).thenAcceptBothAsync(future1, (v1,v2) -> {
            System.out.println(v1 + v2);
        });
        CompletableFuture.supplyAsync(()->10).runAfterBothAsync(future2, () -> System.out.println("结果计算完成"));

        CompletableFuture<Void> future3 = CompletableFuture.supplyAsync(()-> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生成值10");
            return 10;
        }).runAfterEitherAsync(future2, () -> System.out.println("任一future已经计算完成后执行"));
        future3.join();
        TimeUnit.SECONDS.sleep(3);
    }
}
