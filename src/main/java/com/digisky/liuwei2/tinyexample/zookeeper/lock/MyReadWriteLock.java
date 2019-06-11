package com.digisky.liuwei2.tinyexample.zookeeper.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;

/**
 * @author liuwei2
 */
public class MyReadWriteLock {
    private CuratorFramework client;
    private String path;
    private final static String LOCK_NAME = "lock";
    private String Read = "R";
    private String Write = "W";

    public MyReadWriteLock(CuratorFramework client, String path) {
        this.client = client;
        this.path = ZKPaths.makePath(path, LOCK_NAME);
    }

    public void readLock() throws Exception {
        String path = ZKPaths.makePath(this.path, Read);
        client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
    }

    public void unReadLock() {

    }

    public void writeLock() {

    }

    public void unWriteLock() {

    }
}


