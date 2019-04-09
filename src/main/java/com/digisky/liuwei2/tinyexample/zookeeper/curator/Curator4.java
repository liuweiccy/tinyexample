package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Curator删除节点API示例
 * @author liuwei2
 */
@Slf4j
public class Curator4 {
    private static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.101.88:2183, 192.168.101.56:2182")
            .sessionTimeoutMs(3000)
            .connectionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();

        String path = "/zk-book-c1";
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "init".getBytes());

        Stat stat = new Stat();
        String data = String.valueOf(client.getData().storingStatIn(stat).forPath(path));
        log.debug("获取节点的数据：{}", data);

        client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(stat.getVersion()).forPath(path);
    }
}
