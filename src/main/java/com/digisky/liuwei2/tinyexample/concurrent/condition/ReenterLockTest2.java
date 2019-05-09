package com.digisky.liuwei2.tinyexample.concurrent.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.digisky.liuwei2.tinyexample.util.Util;

/**
 * @author liuwei2
 */
public class ReenterLockTest2 implements Runnable {
    final ReentrantLock lock1 = new ReentrantLock();
    final ReentrantLock lock2 = new ReentrantLock();
    int seqNum;

    public ReenterLockTest2(int seqNum) {
        this.seqNum = seqNum;
    }

    @Override
    public void run() {
        try {
            if (seqNum == 1) {
                lock1.lock();
                TimeUnit.SECONDS.sleep(6);
                lock2.lock();
            } else {
                lock2.lock();
                TimeUnit.SECONDS.sleep(3);
                lock1.lock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            Util.print(Thread.currentThread().getName() + " : 线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        ReenterLockTest2 test1 = new ReenterLockTest2(1);
        ReenterLockTest2 test2 = new ReenterLockTest2(2);

        Thread t1 = new Thread(test1);
        Thread t2 = new Thread(test2);
        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(1);

        t2.interrupt();
    }
}
