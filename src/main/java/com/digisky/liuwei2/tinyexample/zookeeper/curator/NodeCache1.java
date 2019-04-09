package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * NodeCache使用示例
 * @author liuwei2
 */
@Slf4j
public class NodeCache1 {
    private static TestingServer server;

    static {
        try {
            server = new TestingServer();
            server.start();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    static String path = "/zk-book/nodecache";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(server.getConnectString())
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .sessionTimeoutMs(1000)
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        final NodeCache cache = new NodeCache(client, path, false);
        cache.start(true);
        // 注册监听器
        cache.getListenable().addListener(() -> {
            if (cache.getCurrentData() == null) {
                log.info("节点被删除");
            } else {
                log.info("节点数据发生变化，当前数据为：{}", new String(cache.getCurrentData().getData()));
            }
        });

        try {
            client.setData().forPath(path, "update".getBytes());
            TimeUnit.SECONDS.sleep(1);
            client.delete().deletingChildrenIfNeeded().forPath(path);
            TimeUnit.SECONDS.sleep(100);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        server.stop();
    }
}
