package com.digisky.liuwei2.tinyexample.jmh;

import java.util.Arrays;

/**
 * 单线程计数器
 *
 * @author liuwei2
 * @date 2019/09/16 16:57
 */
public class SingleCalculator implements Calculator {
    @Override
    public long sum(int[] numbers) {
        return Arrays.stream(numbers).sum();
    }

    @Override
    public void shutdown() {
    }
}
