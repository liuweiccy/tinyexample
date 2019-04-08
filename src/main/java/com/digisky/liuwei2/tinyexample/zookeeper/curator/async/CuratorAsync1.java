package com.digisky.liuwei2.tinyexample.zookeeper.curator.async;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用Curator异步接口
 * @author liuwei2
 */
@Slf4j
public class CuratorAsync1 {
    static String path = "/zk-book-7";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.101.88:2183")
            .sessionTimeoutMs(3000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    static CountDownLatch latch = new CountDownLatch(2);
    static ExecutorService service = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        client.start();
        log.debug("Main thread:" + Thread.currentThread().getName());

        // 异步处理，单独的事件处理器
        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground((client, event) -> {
                log.debug("1----事件返回码：{}，事件类型：{}", event.getResultCode(), event.getType());
                log.debug("1----process thread: {}", Thread.currentThread().getName());
                latch.countDown();
            }, service).forPath(path, "init".getBytes());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        // 异步处理，无单独的处理器
        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground((client, event) -> {
                log.debug("2----事件返回码：{}，事件类型：{}", event.getResultCode(), event.getType());
                log.debug("2----process thread: {}", Thread.currentThread().getName());
                latch.countDown();
            }).forPath(path, "init".getBytes());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        latch.await();
        service.shutdown();
    }
}
