package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 创建节点API示例
 * @author liuwei2
 */
public class Curator3 {

    public static void main(String[] args) throws Exception {
        CuratorFramework client = getCuratorFramework();
        client.start();

        String path = "/zk-book/c1";

        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "init".getBytes());
    }

    private static CuratorFramework getCuratorFramework() {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);

        return CuratorFrameworkFactory.builder()
                .connectString("192.168.101.88:2183")
                .sessionTimeoutMs(1000)
                .retryPolicy(policy)
                .build();
    }
}
