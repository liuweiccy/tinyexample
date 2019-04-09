package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Arrays;

/**
 * Curator获取数据
 * @author liuwei2
 */
@Slf4j
public class Curator5 {
    static String path = "/zk-book-c5";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.101.88:2183")
            .sessionTimeoutMs(1000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();

        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "init".getBytes());

        Stat stat = new Stat();

        String data = Arrays.toString(client.getData().storingStatIn(stat).forPath(path));

        log.debug("data:{}", data);
        log.debug("stat:{}", stat);
    }
}
