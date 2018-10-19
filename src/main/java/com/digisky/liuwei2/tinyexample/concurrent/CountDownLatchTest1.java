package com.digisky.liuwei2.tinyexample.concurrent;

import java.util.concurrent.CountDownLatch;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class CountDownLatchTest1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cd = new CountDownLatch(2);

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000);
                print(Thread.currentThread().getName() + " is done.");
                cd.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.setName("子线程");
        thread.start();

        cd.countDown();
        print(Thread.currentThread().getName() + " is done.");

        cd.await();

        print("end");
    }
}
