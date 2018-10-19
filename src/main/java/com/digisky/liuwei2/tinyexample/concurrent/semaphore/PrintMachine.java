package com.digisky.liuwei2.tinyexample.concurrent.semaphore;

import java.util.concurrent.CountDownLatch;

/**
 * @author liuwei2
 */
public class PrintMachine implements Runnable {
    private PrintQueue printQueue;
    private CountDownLatch latch;

    public PrintMachine(PrintQueue queue, CountDownLatch latch) {
        this.printQueue = queue;
        this.latch = latch;
    }

    @Override
    public void run() {
        printQueue.printing();
        latch.countDown();
    }
}
