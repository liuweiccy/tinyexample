package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * JDK自带的CyclicBarrier
 * @author liuwei2
 */
@Slf4j
public class JVMCyclicBarrier {
    static CyclicBarrier barrier = new CyclicBarrier(3);
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(new Player("1号"));
        service.submit(new Player("2号"));
        service.submit(new Player("3号"));
        service.shutdown();
    }
}

@Slf4j
@AllArgsConstructor
class Player implements Runnable {
    private String name;

    @Override
    public void run() {
        log.info("{}选手准备好了！！！", name);
        try {
            JVMCyclicBarrier.barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            log.error(e.getMessage(), e);
        }
        log.info("{}选手起跑！！", name);
    }
}
