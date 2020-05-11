package com.digisky.liuwei2.concurrent.designpattern.threadlocal;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liuwei2
 * 2020/10/6 17:36
 */
public class ThreadId {
    private final static AtomicLong ID = new AtomicLong(0);
    private final static ThreadLocal<Long> THREAD_LOCAL = ThreadLocal.withInitial(ID::incrementAndGet);

    long get() {
        return THREAD_LOCAL.get();
    }
}
