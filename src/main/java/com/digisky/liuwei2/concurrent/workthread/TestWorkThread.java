package com.digisky.liuwei2.concurrent.workthread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author liuwei2
 * 2020/5/11 16:02
 */
public class TestWorkThread {
    private static final BlockingQueue<Integer> QUEUE = new ArrayBlockingQueue<>(10000);
    private static final int SEND = 100;

    public static void main(String[] args) {
        final Random random = new Random();
        for (int i = 0; i < SEND; i++) {
            new Thread(() -> {
                int r = random.nextInt(1000);
                QUEUE.offer(r);
            }).start();
        }

        ProcessData<Integer> processData = new ProcessData<>(QUEUE);
        processData.run();
    }
}
