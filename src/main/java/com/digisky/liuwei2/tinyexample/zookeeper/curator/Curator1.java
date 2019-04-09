package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * 使用Curator来创建一个ZooKeeper客户端
 * @author liuwei2
 */
public class Curator1 {
    public static void main(String[] args) throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.101.88:2183", 5000, 5000, retryPolicy);
        client.start();
        TimeUnit.SECONDS.sleep(100);
    }
}
