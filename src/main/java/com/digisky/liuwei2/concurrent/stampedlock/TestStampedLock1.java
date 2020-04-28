package com.digisky.liuwei2.concurrent.stampedlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock当使用不可中断的readLock时，会导致
 * 当前使用的线程进入死循环。
 *
 * @author liuwei2
 * 2020/4/27 10:35
 */
public class TestStampedLock1 {
    private final static StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            lock.writeLock();
            // 阻塞。
            LockSupport.park();
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            // 被写锁阻塞。
            lock.readLock();
        });
        t2.start();
        TimeUnit.SECONDS.sleep(1);

        // 中断线程t2，会导致t2线程进入死循环。
        t2.interrupt();
        t2.join();
    }
}
