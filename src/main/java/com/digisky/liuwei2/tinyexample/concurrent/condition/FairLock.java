package com.digisky.liuwei2.tinyexample.concurrent.condition;

import java.util.concurrent.locks.ReentrantLock;

import com.digisky.liuwei2.tinyexample.util.Util;

/**
 * @author liuwei2
 */
public class FairLock implements Runnable {
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            lock.lock();
            Util.print(Thread.currentThread().getName() + " : 获得锁!");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        FairLock fairLock = new FairLock();

        Thread t1 = new Thread(fairLock, "线程1");
        Thread t2 = new Thread(fairLock, "线程2");

        t1.start();
        t2.start();
    }
}
