package com.digisky.liuwei2.tinyexample.concurrent.condition;

import com.digisky.liuwei2.tinyexample.util.Util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuwei2
 */
public class ReenterLockTest1 implements Runnable {
    public static int sum = 0;
    public static final int COUNT = 10000;

    private static final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        for (int i = 0; i < COUNT; i ++) {
            lock.lock();
            try {
                sum ++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockTest1 test1 = new ReenterLockTest1();
        Thread t1 = new Thread(test1);
        Thread t2 = new Thread(test1);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        Util.print(sum);
    }
}
