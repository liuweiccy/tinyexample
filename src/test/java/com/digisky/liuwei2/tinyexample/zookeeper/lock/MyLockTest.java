package com.digisky.liuwei2.tinyexample.zookeeper.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liuwei2
 */
@Slf4j
public class MyLockTest {
    private static String lock_path = "/R_W_lock_path";
    private static CuratorFramework client;

    @Before
    public void init() {
        client = CuratorFrameworkFactory.builder()
                .connectString("192.168.101.88:2183")
                .sessionTimeoutMs(3000)
                .connectionTimeoutMs(1000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
    }

    @Test
    public void testMyLock() throws InterruptedException {
        client.start();

        final InterProcessLock lock = new MyLock(client, lock_path);
        final CountDownLatch latch = new CountDownLatch(1);
        final int[] index = {0};
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

        TimeUnit.SECONDS.sleep(10);
    }
}
