package com.digisky.liuwei2.concurrent.threadlocal;

/**
 * @author liuwei2
 * 2020/9/5 17:35
 */
public class TestThreadLocal {
    public static void main(String[] args) throws InterruptedException {
        final ThreadId threadId = new ThreadId();

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": " + threadId.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadId.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadId.get());
        });
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": " + threadId.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadId.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadId.get());
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(Thread.currentThread().getName() + ": " + threadId.get());
    }
}
