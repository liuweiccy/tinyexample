package com.digisky.liuwei2.tinyexample.zookeeper.lock;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

import lombok.extern.slf4j.Slf4j;

/**
 * zookeeper的分布式锁--排他锁
 * @author liuwei2
 */
@Slf4j
public class MyLock implements InterProcessLock {
    private CuratorFramework client;
    private String path;
    private final String LOCK_NAME = "lock";

    public MyLock(CuratorFramework client, String path) {
        this.client = client;
        this.path = ZKPaths.makePath(path, LOCK_NAME);
    }

    @Override
    public void acquire() throws Exception {
        try {
            client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception e) {
            if (e instanceof KeeperException.NodeExistsException) {
                PathChildrenCache childrenCache = new PathChildrenCache(client, ZKPaths.getPathAndNode(path).getPath(), true);
                childrenCache.getListenable().addListener((client, event) -> {
                    if (event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
                        acquire();
                    }
                });
                childrenCache.start();
            } else {
                log.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public boolean acquire(long time, TimeUnit unit) throws Exception {
        return false;
    }

    @Override
    public void release() throws Exception {
        Stat stat = client.checkExists().creatingParentContainersIfNeeded().forPath(path);
        if (stat != null && stat.getVersion() != 0) {
            client.delete().deletingChildrenIfNeeded().forPath(path);
        }
    }

    @Override
    public boolean isAcquiredInThisProcess() {
        return false;
    }
}
