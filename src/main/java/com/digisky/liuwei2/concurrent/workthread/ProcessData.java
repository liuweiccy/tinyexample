package com.digisky.liuwei2.concurrent.workthread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.jetbrains.annotations.NotNull;


/**
 * @author liuwei2
 * 2020/5/11 15:38
 */
public class ProcessData<T> {
    private final BlockingQueue<T> queue;
    private static final ExecutorService SERVICE = new ThreadPoolExecutor(0, 6
            , 10, TimeUnit.SECONDS
            , new ArrayBlockingQueue<>(6)
            , new ThreadFactory() {
        private final AtomicInteger atomicInteger = new AtomicInteger(0);

        @Override
        public Thread newThread(@NotNull Runnable r) {
            return new Thread(r, "数据处理线程Pool-Thread-" + atomicInteger.getAndIncrement());
        }
    }
            , (r, executor) -> {
                if (!executor.isShutdown()) {
                    try {
                        System.out.println("线程池已满，等待100ms再执行");
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("线程被中断");
                    }
                    executor.execute(r);
                }
            });

    public ProcessData(BlockingQueue<T> queue) {
        this.queue = queue;
    }


    public void run() {
        while (true) {
            try {
                final T t = this.queue.take();
                SERVICE.submit(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(150);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + "：线程被中断");
                    }
                    System.out.println(t);
                });
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "：线程被中断");
            }
        }
    }
}
