package com.digisky.liuwei2.tinyexample.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 测试使用
 * @author liuwei2
 * 2019/12/25 17:10
 */
public class FutureTaskTest {
    /** 可以并发访问的Map */
    private final ConcurrentMap<String, Future<String>> taskCache = new ConcurrentHashMap<>();

    private String executionTask(final String taskName) {
        while (true) {
            Future<String> future = taskCache.get(taskName);

            if (future == null) {
                System.out.println(Thread.currentThread().getName() + ":准备初始化taskName:" + taskName);
                Callable<String> task = () -> taskName;
                FutureTask<String> futureTask = new FutureTask<>(task);
                future = taskCache.putIfAbsent(taskName, futureTask);

                // 初始化taskName对应的Future成功，仅需要一次成功
                if (future == null) {
                    System.out.println(Thread.currentThread().getName() + ":初始化成功taskName:" + taskName);
                    future = futureTask;
                    futureTask.run();
                } else {
                    System.out.println(Thread.currentThread().getName() + ":taskName已经被初始化了");
                }
            }

            try {
                System.out.println(Thread.currentThread().getName() + ":获取结果值");
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                taskCache.remove(taskName);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final String name = "testTask";
        final FutureTaskTest test = new FutureTaskTest();

        Thread t1 = new Thread(() -> test.executionTask(name), "线程--1");
        Thread t2 = new Thread(() -> test.executionTask(name), "线程--2");
        Thread t3 = new Thread(() -> test.executionTask(name), "线程--3");

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(1000);
    }
}
