package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import lombok.extern.slf4j.Slf4j;

/**
 * 分布式锁示例
 * MyLock是自我实现的分布式排他锁
 * 如果在没有加锁的情况下
 * @author liuwei2
 */
@Slf4j
public class RecipesLock {
    static String lock_path = "/curator_recipes_lock_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.101.88:2183")
            .sessionTimeoutMs(3000)
            .connectionTimeoutMs(1000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) {
        client.start();

        final InterProcessLock lock = new InterProcessMutex(client, lock_path);
        final CountDownLatch latch = new CountDownLatch(1);
        final int[] index = {0};
        long s = System.nanoTime();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                    lock.acquire();
                    log.info("生成的订单号：{}", ++index[0]);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                } finally {
                    try {
                        lock.release();
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }).start();
        }
        latch.countDown();

        System.out.println(System.nanoTime() - s);
    }
}
