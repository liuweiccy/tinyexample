package com.digisky.liuwei2.concurrent.hashmap;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liuwei2
 * 2020/4/27 18:08
 */
public class TestHashMap {
    private HashMap<Long, Integer> hashMap = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        final TestHashMap testHashMap = new TestHashMap();
        final Random random = new Random();
        final CountDownLatch latch = new CountDownLatch(1000);
        final AtomicLong atomicLong = new AtomicLong(0);
        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                testHashMap.hashMap.put(atomicLong.getAndIncrement(), random.nextInt(1000));
                latch.countDown();
            }).start();
        }
        latch.await();
    }
}
