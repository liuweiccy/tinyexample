package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import lombok.extern.slf4j.Slf4j;

/**
 * 分布式Barrier
 * @author liuwei2
 */
@Slf4j
public class RecipesBarrier {
    static String barrier_path = "/curator_recipes_barrier_path";
    static DistributedBarrier barrier;

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                CuratorFramework client = CuratorFrameworkFactory.builder()
                        .connectString("192.168.101.88:2183")
                        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                        .build();

                client.start();

                barrier = new DistributedBarrier(client, barrier_path);
                log.info(Thread.currentThread().getName() + "号barrier设置");
                try {
                    barrier.setBarrier();
                    barrier.waitOnBarrier();
                    log.info("启动....");
                } catch (Exception e) {
                    log.info(e.getMessage(), e);
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(10);
        barrier.removeBarrier();
    }
}
