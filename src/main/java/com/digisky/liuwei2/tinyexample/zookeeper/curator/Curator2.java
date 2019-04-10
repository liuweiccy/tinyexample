package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * 使用fluent风格创建
 * @author liuwei2
 */
@Slf4j
public class Curator2 {
    public static void main(String[] args) throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3 , 3000);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.101.56:2182")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("curator2")
                .build();
        log.debug("当前客户端:{}", client.toString());
        client.start();
        log.debug("当前客户端:{}", client.toString());

        TimeUnit.SECONDS.sleep(100);
    }
}
