package com.digisky.liuwei2.java8inaction.chapter5;

import java.util.OptionalInt;
import java.util.stream.IntStream;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * lambda归约（reduce）
 *
 * @author liuwei2
 * @date 2019/10/15 18:38
 */
public class ReduceTest {
    public static void main(String[] args) {
        OptionalInt optional1 = IntStream.range(1, 5).reduce(Integer::sum);
        print(optional1.orElse(-1));

        int optional2 = IntStream.range(1, 5).reduce(10, Integer::sum);
        print(optional2);

        OptionalInt min = IntStream.range(1, 10).reduce(Integer::min);
        int max = IntStream.range(1, 10).reduce(0, Integer::max);
        print("max:" + max);
        print("min:" + min.getAsInt());

        OptionalInt min1 = IntStream.range(1, 10).min();
        int max1 = IntStream.range(1, 10).reduce(0, Integer::max);
        print("max1:" + max1);
        print("min1:" + min1.getAsInt());
    }
}
