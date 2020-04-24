package com.digisky.liuwei2.concurrent.semaphore;

import java.util.concurrent.TimeUnit;

/**
 * @author liuwei2
 * 2020/4/23 18:19
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        ObjPool<String, Integer> objPool = new ObjPool<>(10, "Hello");
        for (int i = 0; i < 100; i++) {
            new Thread(() -> objPool.exec(String::length)).start();
        }
        TimeUnit.SECONDS.sleep(1000);
    }
}
