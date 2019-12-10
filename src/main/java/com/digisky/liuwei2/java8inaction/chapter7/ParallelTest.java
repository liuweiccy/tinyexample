package com.digisky.liuwei2.java8inaction.chapter7;

import java.util.function.Function;

/**
 * @author liuwei2
 */
public class ParallelTest {
    public static long measureSumPerf(Function<Long, Long> addr, long n) {
        long fast = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = addr.apply(n);
            long duration = (long) ((System.nanoTime() - start) / 1e6);
            if (duration < fast) {
                fast = duration;
            }
        }
        return fast;
    }


    public static void main(String[] args) {
        long n = 100_000_000;
        System.out.println("传统for循环执行时间：" + measureSumPerf(ParallelStreams::forSum, n) + " ms");
        System.out.println("seq执行时间：" + measureSumPerf(ParallelStreams::seqSum, n) + " ms");
        System.out.println("parallel执行时间：" + measureSumPerf(ParallelStreams::parallelSum, n) + " ms");
        System.out.println("seqItr执行时间：" + measureSumPerf(ParallelStreams::seqItrSum, n) + " ms");
        System.out.println("parallelItr执行时间：" + measureSumPerf(ParallelStreams::parallelItrSum, n) + " ms");
    }
}
