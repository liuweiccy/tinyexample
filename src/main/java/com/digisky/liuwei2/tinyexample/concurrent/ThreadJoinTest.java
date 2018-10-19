package com.digisky.liuwei2.tinyexample.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author liuwei2
 */
public class ThreadJoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程["+ Thread.currentThread().getName() +"]执行完成");
        });

        thread.setName("sleep5");
        thread.start();
        System.out.println("子线程开始执行");

        thread.join();
        System.out.println("main end");
    }
}
