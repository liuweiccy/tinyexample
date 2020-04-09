package com.digisky.liuwei2.tinyexample.future;

import java.util.concurrent.CompletableFuture;

/**
 * @author liuwei2
 * 2020/4/9 11:38
 */
public class TestCompletableFuture3 {
    private static final double LEN = 3;

    public static void main(String[] args) {
        CompletableFuture<Double> res = CompletableFuture.supplyAsync(() -> LEN).thenApply(len -> len*len).thenApply(v -> Math.PI * v);
        System.out.println(res.join());
    }
}
