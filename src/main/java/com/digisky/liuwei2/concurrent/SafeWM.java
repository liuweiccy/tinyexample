package com.digisky.liuwei2.concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 并发程序仓库上、下限实验
 * @author liuwei2
 * 2020/4/20 16:25
 */
public class SafeWM {
    private final AtomicLong upper = new AtomicLong(0);
    private final AtomicLong lower = new AtomicLong(0);

    synchronized void setUpper(long v) {
        if (v < lower.get()) {
            throw new IllegalArgumentException();
        }
        upper.set(v);
    }

    synchronized void setLower(long v) {
        if (v > upper.get()) {
            throw new IllegalArgumentException();
        }
        lower.set(v);
    }
}
