package com.digisky.liuwei2.concurrent.atomic;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author liuwei2
 * 2020/4/28 19:24
 */
public class TestAtomicIntegerFieldUpdater {
    private static AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(TestAtomicIntegerFieldUpdater.class, "n");
    private volatile int n = 100;

    public static void main(String[] args) throws InterruptedException {
        TestAtomicIntegerFieldUpdater test = new TestAtomicIntegerFieldUpdater();
        final Random random = new Random();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (updater.compareAndSet(test, 100, 120)) {
                    System.out.println(Thread.currentThread().getName() + "：修改成功");
                } else {
                    System.out.println(Thread.currentThread().getName() + "：XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(1);
    }
}
