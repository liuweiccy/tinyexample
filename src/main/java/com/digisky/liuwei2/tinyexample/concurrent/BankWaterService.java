package com.digisky.liuwei2.tinyexample.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class BankWaterService implements Runnable {
    private CyclicBarrier cb = new CyclicBarrier(4, this);

    private ExecutorService executors = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String, Integer> total = new ConcurrentHashMap<>();

    private void count() {
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            executors.execute(() -> {
                total.put(Thread.currentThread().getName(), finalI);
                try {
                    print(Thread.currentThread().getName());
                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                print(Thread.currentThread().getName());
            });
        }
    }


    @Override
    public void run() {
        int result = 0;
        for (int i : total.values()) {
            result += i;
        }

        print(result);
    }

    private void shutdown() {
        executors.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        BankWaterService service = new BankWaterService();
        service.count();
        Thread.sleep(5000);
        service.shutdown();
        Thread.sleep(5000);
    }
}
