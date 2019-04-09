package com.digisky.liuwei2.tinyexample.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 创建可复用sessionId和sessionPwd的对象
 *
 * @author liuwei2
 */
@Slf4j
public class ZooKeeperTest2 implements Watcher {
    private static CountDownLatch connectedLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zk1 = new ZooKeeper("192.168.101.88:2183", 5000, new ZooKeeperTest2());
        long sessionId = zk1.getSessionId();
        byte[] sessionPwd = zk1.getSessionPasswd();
        connectedLatch.await();
        log.debug("zk1---------" + zk1.toString());


        ZooKeeper zk2 = new ZooKeeper("192.168.101.88:2183", 5000, new ZooKeeperTest2(), sessionId, sessionPwd);
        TimeUnit.SECONDS.sleep(10);
        log.debug("zk2---------" + zk2.toString());
    }

    @Override
    public void process(WatchedEvent event) {
        log.debug("接收到的监视状态：{}", event.getState());
        if (event.getState() == Event.KeeperState.SyncConnected) {
            connectedLatch.countDown();
        }
    }
}
