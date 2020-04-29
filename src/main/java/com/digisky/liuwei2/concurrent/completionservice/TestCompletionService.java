package com.digisky.liuwei2.concurrent.completionservice;

import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试CompletionService
 *
 * @author liuwei2
 * 2020/4/29 16:47
 */
public class TestCompletionService {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(3);
    private static final CompletionService<Integer> SERVICE = new ExecutorCompletionService<>(EXECUTOR_SERVICE);

    int value() {
        Random random = new Random();
        int v = random.nextInt(10) + 1;
        try {
            TimeUnit.SECONDS.sleep(v);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return v;
    }

    public static void main(String[] args) {
        TestCompletionService test = new TestCompletionService();
        SERVICE.submit(test::value);
        SERVICE.submit(test::value);
        SERVICE.submit(test::value);

        System.out.println("根据先后顺序获取：");

        for (int i = 0; i < 3; i++) {
            try {
                System.out.println(SERVICE.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("执行完成");
        System.exit(0);
    }
}
