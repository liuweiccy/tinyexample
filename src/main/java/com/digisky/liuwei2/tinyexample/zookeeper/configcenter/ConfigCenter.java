package com.digisky.liuwei2.tinyexample.zookeeper.configcenter;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置中心的发布订阅模式
 * @author liuwei2
 */
@Slf4j
@Builder
public class ConfigCenter {
    private CuratorFramework client;

    public void pub(String path, byte[] data) {
        try {
            checkStat();
            Stat stat = client.checkExists().forPath(path);
            if (stat == null) {
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(path, data);
            } else {
                client.setData().withVersion(stat.getVersion()).forPath(path, data);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void checkStat() {
        if (client.getState() != CuratorFrameworkState.STARTED) {
            client.start();
        }
    }

    public void sub(String path, CallBack callBacK) {
        checkStat();
        NodeCache cache = new NodeCache(client, path);
        try {
            cache.start(true);
            cache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    callBacK.handle(cache);
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
