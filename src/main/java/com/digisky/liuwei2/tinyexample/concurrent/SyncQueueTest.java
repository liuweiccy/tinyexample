package com.digisky.liuwei2.tinyexample.concurrent;

import java.util.concurrent.SynchronousQueue;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class SyncQueueTest {
    public static void main(String[] args) {
        final SynchronousQueue<String> queue = new SynchronousQueue<>();

        Thread putThread = new Thread(() -> {
            try {
                print("put thread start");
                queue.put("num");
                print("put thread end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread takeThread = new Thread(() -> {
            try {
                print("take thread start");
                queue.take();
                print("take thread end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        putThread.start();
        takeThread.start();
    }
}
