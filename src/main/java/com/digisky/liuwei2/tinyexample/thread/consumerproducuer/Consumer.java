package com.digisky.liuwei2.tinyexample.thread.consumerproducuer;

import java.util.List;
import java.util.Random;

/**
 * @author liuwei2
 * 2020/4/1 16:44
 */
public class Consumer extends Thread {
    private final List<String> depot;

    public Consumer(List<String> depot) {
        this.depot = depot;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(333);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (depot) {
                if (depot.isEmpty()) {
                    try {
                        depot.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int size = depot.size();
                int num = new Random().nextInt(size) + 1;
                depot.subList(0, num).clear();
                System.out.println("线程：" + this.getName() + "；消耗数量：" + num);
                if (depot.size() < 100) {
                    depot.notify();
                }
            }
        }
    }
}
