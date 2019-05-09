package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.test.TestingServer;
import org.apache.zookeeper.CreateMode;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试服务器示例
 * @author liuwei2
 */
@Slf4j
public class TestingServer1 {
    static String path = "/zk-book";

    public static void main(String[] args) throws Exception {
        TestingServer server = new TestingServer(2185);

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(server.getConnectString())
                .retryPolicy(new RetryNTimes(3, 1000))
                .build();

        client.start();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path + "/c11", "init".getBytes());
        log.info("获取目录的子节点：{}", client.getChildren().forPath(path));
        server.close();
    }
}
