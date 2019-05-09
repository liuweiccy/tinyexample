package com.digisky.liuwei2.tinyexample.concurrent.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.digisky.liuwei2.tinyexample.util.Util;

/**
 * @author liuwei2
 */
public class ReenterLockTest3 implements Runnable {
    static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                Util.print(Thread.currentThread().getName() + ": 获取锁成功!");
                TimeUnit.SECONDS.sleep(5);
            } else {
                Util.print(Thread.currentThread().getName() + ": 获取锁失败!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReenterLockTest3 test3 = new ReenterLockTest3();

        Thread t1 = new Thread(test3);
        Thread t2 = new Thread(test3);
        t1.start();
        t2.start();
    }
}
