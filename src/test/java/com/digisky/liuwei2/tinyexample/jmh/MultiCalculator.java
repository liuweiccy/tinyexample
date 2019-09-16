package com.digisky.liuwei2.tinyexample.jmh;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;

/**
 * 多线程计数器
 *
 * @author liuwei2
 * @date 2019/09/16 16:59
 */
public class MultiCalculator implements Calculator {
    private int coreNum;

    public MultiCalculator(int coreNum) {
        this.coreNum = coreNum;
    }

    @Override
    public long sum(int[] numbers) {
        return Arrays.stream(numbers).parallel().sum();
    }

    @Override
    public void shutdown() {

    }
}
