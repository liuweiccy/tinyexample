package com.digisky.liuwei2.tinyexample.concurrent.threadlocal;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class ThreadLocalTest1 {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static class myRunnable implements Runnable {

        @Override
        public void run() {
            int value = (int) (Math.random() * 100);
            print("设置值：" + value);
            threadLocal.set(value);
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print(Thread.currentThread().getName() +": "+ threadLocal.get() + "-------" + threadLocal);
        }
    }


    public static void main(String[] args) {
        int NUMS = 2;
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("ThreadLocal-Pool-%d").build();
        ExecutorService service = new ThreadPoolExecutor(NUMS, 2 * NUMS, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1000), factory, new ThreadPoolExecutor.AbortPolicy());

        myRunnable runnable = new myRunnable();

        service.submit(runnable);
        service.submit(runnable);
        service.submit(runnable);
        service.submit(runnable);
        service.submit(runnable);
        service.submit(runnable);
        service.submit(runnable);
        service.submit(runnable);
        service.submit(runnable);
        service.submit(runnable);
        service.submit(runnable);
        service.submit(runnable);
        service.submit(runnable);
        service.shutdown();
    }
}
