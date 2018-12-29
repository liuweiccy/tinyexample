package com.digisky.liuwei2.tinyexample.concurrent.condition;

import com.digisky.liuwei2.tinyexample.util.Util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuwei2
 */
public class ConditionTest1 implements Runnable {
    final static Lock lock = new ReentrantLock();
    final static Condition condition = lock.newCondition();


    @Override
    public void run() {
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Util.print("子线程执行完毕");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionTest1 test1 = new ConditionTest1();

        Thread t1 = new Thread(test1, "子线程");
        t1.start();

        TimeUnit.SECONDS.sleep(3);

         Util.print("主线程通知子线程继续执行");
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
