package com.digisky.liuwei2.concurrent.designpattern.guardedsuspension;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * @author liuwei2
 * 2020/5/9 17:07
 */
public class GuardedObject<T> {
    private T value;

    private static final Lock LOCK = new ReentrantLock();
    private static final Condition DONE = LOCK.newCondition();
    private static final Integer TIMEOUT = 1;

    T get(Predicate<T> p) {
        LOCK.lock();
        try {
            while (!p.test(value)) {
                DONE.await(TIMEOUT, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
        return value;
    }

    void onChange(T value) {
        LOCK.lock();
        try {
            this.value = value;
            DONE.signalAll();
        } finally {
            LOCK.unlock();
        }
    }
}
