package com.digisky.liuwei2.tinyexample.zookeeper.lock;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.curator.framework.CuratorFramework;

/**
 * 基于Zookeeper,自我实现的读写锁
 *
 * @author liuwei2
 * @date 2019/07/09 17:33
 */
public class MyReadWriteLock {
    private CuratorFramework client;
    private String path;
    private AtomicLong counter = new AtomicLong(0);

    public MyReadWriteLock(CuratorFramework client, String path) {
        this.client = client;
        this.path = path;
    }

    public Lock readLock() {
        return new ReadLock(client, path, counter);
    }

    public Lock writeLock() {
        return new WriteLock(client, path, counter);
    }
}
