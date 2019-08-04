package com.digisky.liuwei2.tinyexample.guava;

import com.google.common.eventbus.EventBus;

import java.util.concurrent.CountDownLatch;

/**
 * 测试Guava的EventBus
 * @author Eric Liu
 * @date 2019/08/04 22:20
 */
public class EventBusTest {
    public static void main(String[] args) throws InterruptedException {
        EventBus bus = new EventBus("test");
        int N = 1000000;
        CountDownLatch latch = new CountDownLatch(N);

        bus.register(new ExampleListener(latch));

        long s = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            bus.post(new ExampleEvent(i));
        }
        latch.await();
        long e = System.currentTimeMillis();
        System.out.println("总共耗费时间：" + (e - s) + " ms");
    }
}
