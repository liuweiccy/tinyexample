package com.digisky.liuwei2.tinyexample.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author liuwei2
 * 2020/4/7 18:04
 */
public class TestCompletableFuture1 {
    private static Random rand = new Random();
    private static long t = System.currentTimeMillis();

    static int getMoreData() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // throw new RuntimeException("运行时测试异常");
        System.out.println("end to start compute.passed " + (System.currentTimeMillis() - t) / 1000 + " seconds");
        return rand.nextInt(1000);
    }

    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(TestCompletableFuture1::getMoreData);
        future.whenComplete((x, y) -> {
            System.out.println("完成回调结果：" + x);
            System.out.println("是否有异常：" + y);
        });

        System.out.println(future.join());
    }
}
