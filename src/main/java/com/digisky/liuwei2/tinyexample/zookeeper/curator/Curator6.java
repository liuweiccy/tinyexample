package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * 更新数据API
 * @author liuwei2
 */
@Slf4j
public class Curator6 {
    static String path = "/zk-book-6";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.101.56:2182")
            .sessionTimeoutMs(3000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "init_zk_book_6".getBytes());

        Stat stat = new Stat();
        byte[] data = client.getData().storingStatIn(stat).forPath(path);
        log.debug("返回数据：{}", data);
        log.debug("原始数据：{}", "init_zk_book_6".getBytes());

        client.setData().withVersion(stat.getVersion()).forPath(path).getVersion();
    }
}
