package com.digisky.liuwei2.tinyexample.thread.consumerproducuer;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author liuwei2
 * 2020/4/1 16:58
 */
public class Producer extends Thread {
    private final List<String> depot;

    public Producer(List<String> depot) {
        this.depot = depot;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (depot) {
                if (depot.size() < 1000) {
                    int num = new Random().nextInt(100) + 1;
                    IntStream.range(0, num).forEach(v -> depot.add(String.valueOf(v)));
                    System.out.println("线程：" + this.getName() + "；生产数量：" + num);
                    depot.notify();
                } else {
                    try {
                        depot.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
