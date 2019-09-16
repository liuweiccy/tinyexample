package com.digisky.liuwei2.tinyexample.jmh;

/**
 * 计数器
 *
 * @author liuwei2
 * @date 2019/09/16 16:53
 */
public interface Calculator {
    long sum(int[] numbers);
    void shutdown();
}
