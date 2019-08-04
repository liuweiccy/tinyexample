package com.digisky.liuwei2.tinyexample.concurrent.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.digisky.liuwei2.tinyexample.util.Util;

/**
 * @author liuwei2
 */
public class TryLock implements Runnable {
    static ReentrantLock lock1 = new ReentrantLock();
    static ReentrantLock lock2 = new ReentrantLock();

    private int seqNum;

    public TryLock(int seqNum) {
        this.seqNum = seqNum;
    }

    @Override
    public void run() {
        if (seqNum == 1) {
             if (lock1.tryLock()) {
                 try {
                     TimeUnit.SECONDS.sleep(1);
                     if (lock2.tryLock()) {
                         Util.print(Thread.currentThread().getName() + " : My Job done!");
                         lock2.unlock();
                     } else {
                         Util.print(Thread.currentThread().getName() + " : 未获得lock2!");
                     }
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 } finally {
                     lock1.unlock();
                 }
             } else {
                 Util.print(Thread.currentThread().getName() + " : 未获得lock1!");
             }
        } else {
            if (lock2.tryLock()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    if (lock1.tryLock()) {
                        Util.print(Thread.currentThread().getName() + " : My Job done!");
                        lock1.unlock();
                    } else {
                        Util.print(Thread.currentThread().getName() + " : 未获得lock1!");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock2.unlock();
                }
            } else {
                Util.print(Thread.currentThread().getName() + " : 未获得lock2!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TryLock r1 = new TryLock(1);
        TryLock r2 = new TryLock(2);

        Thread t1 = new Thread(r1);
        t1.setName("第一子线程");

        Thread t2 = new Thread(r2);
        t2.setName("第二子线程");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
