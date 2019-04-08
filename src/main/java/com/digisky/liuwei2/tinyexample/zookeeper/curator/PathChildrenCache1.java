package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liuwei2
 */
@Slf4j
public class PathChildrenCache1 {
    static String path = "/zk-book";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.101.88:2183")
            .sessionTimeoutMs(3000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();

        PathChildrenCache cache = new PathChildrenCache(client, path, true);
        try {
            cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            cache.getListenable().addListener((curatorFramework, event) -> {
                switch (event.getType()) {
                    case CHILD_ADDED:
                        log.debug("新增节点：{}", event.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        log.debug("更新节点：{}", event.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        log.debug("删除节点：{}", event.getData().getPath());
                        break;
                    default:
                        log.debug("非关注事件：{}", event.getType());
                        break;
                }
            });
        } catch (Exception e) {
            log.error("初始化PathChildrenCache出现错误，错误信息：{}", e.getMessage(), e);
        }

        // 创建父节点
//        client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
//        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
//        TimeUnit.SECONDS.sleep(1);

        // 创建节点
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path + "/pcc1");
        TimeUnit.SECONDS.sleep(1);

        // 删除子节点
        client.delete().forPath(path + "/pcc1");
        TimeUnit.SECONDS.sleep(1);

        // 删除自身节点
        client.delete().forPath(path);
        TimeUnit.SECONDS.sleep(100);
    }
}
