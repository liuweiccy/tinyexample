package com.digisky.liuwei2.tinyexample.concurrent.semaphore;

import java.util.concurrent.CountDownLatch;

/**
 * @author liuwei2
 */
public class PrintMachineTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(50);
        PrintQueue queue = new PrintQueue(20);

        for (int i = 0; i < 50; i++) {
            new Thread(new PrintMachine(queue, latch)).start();
        }

        latch.await();
    }
}
