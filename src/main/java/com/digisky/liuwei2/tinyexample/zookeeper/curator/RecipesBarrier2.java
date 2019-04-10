package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import lombok.extern.slf4j.Slf4j;

/**
 * 分布式barrier示例2
 * @author liuwei2
 */
@Slf4j
public class RecipesBarrier2 {
    static String barrier_path = "/curator_recipes_barrier_path2";

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                CuratorFramework client = CuratorFrameworkFactory.builder()
                        .connectString("192.168.101.88:2183")
                        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                        .build();

                client.start();
                DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client, barrier_path, 5);
                try {
                    TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3));
                    log.info("{}号进入barrier!!!", Thread.currentThread().getName());
                    barrier.enter();
                    log.info("启动...");
                    TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3));
                    barrier.leave();
                    log.info("退出...");
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }).start();
        }
    }
}
