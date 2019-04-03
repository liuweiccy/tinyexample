package com.digisky.liuwei2.tinyexample.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liuwei2
 */
@Slf4j
public class ZooKeeperTest1 implements Watcher {
    private static CountDownLatch connectedLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            ZooKeeper zk1 = new ZooKeeper("192.168.101.88:2183", 5000, new ZooKeeperTest1());
            log.debug("zookeeper状态：{}", zk1.getState());
            connectedLatch.await();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        log.debug("收到接受事件：{}", event);
        if (event.getState() == Event.KeeperState.SyncConnected) {
            connectedLatch.countDown();
        }
    }
}
