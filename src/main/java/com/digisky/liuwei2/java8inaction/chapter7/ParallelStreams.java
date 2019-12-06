package com.digisky.liuwei2.java8inaction.chapter7;

import java.util.stream.LongStream;

/**
 * @author liuwei2
 */
public class ParallelStreams {

    public static long forSum(long n) {
        long sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    public static long seqItrSum(long n) {
        return LongStream.iterate(1, i -> i + 1).limit(n).sum();
    }

    public static long parallelItrSum(long n) {
        return LongStream.iterate(1, i -> i + 1).limit(n).parallel().sum();
    }

    public static long seqSum(long n) {
        return LongStream.rangeClosed(0, n).sum();
    }

    public static long parallelSum(long n) {
        return LongStream.rangeClosed(0, n).parallel().sum();
    }
}
