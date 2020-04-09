package com.digisky.liuwei2.tinyexample.future;

import java.util.concurrent.CompletableFuture;

import lombok.Data;

/**
 * @author liuwei2
 * 2020/4/9 11:02
 */
public class TestCompletableFuture2 {
    private static final int SIDE_LEN = 10;

    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> SIDE_LEN);
        CompletableFuture<Summary> summaryFuture = future.handle((v, e) -> {
            Summary summary = new Summary();
            if (e == null) {
                summary.sideLen = v;
                summary.perimeter = v << 2;
            } else {
                System.out.println(e.getMessage());
                throw new RuntimeException("计算周长出现错误");
            }
            return summary;
        });

        CompletableFuture<Summary> summaryFutureFinal = summaryFuture.handle((s,e) -> {
            if (e == null) {
                s.area = s.sideLen << 1;
            } else {
                System.out.println(e.getMessage());
                throw new RuntimeException("计算面积出现错误");
            }
            return s;
        });

        System.out.println(summaryFutureFinal.join());
    }


    @Data
    static
    class Summary {
        private int sideLen;
        private int area;
        private int perimeter;
    }
}
