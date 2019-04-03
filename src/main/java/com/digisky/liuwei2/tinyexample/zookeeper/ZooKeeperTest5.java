package com.digisky.liuwei2.tinyexample.zookeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liuwei2
 */
@Slf4j
public class ZooKeeperTest5 implements Watcher {
    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zk = null;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String path = "/zk-book-test5";

        // 创建链接
        zk = new ZooKeeper("192.168.101.88:2183", 5000, new ZooKeeperTest5());
        latch.await();

        zk.create(path, "test data1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create(path + "/c1", "test data2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        List<String> childrenList = zk.getChildren(path, true);
        log.debug("子节点列表:{}", childrenList);

        zk.create(path + "/c2", "test data3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        TimeUnit.SECONDS.sleep(100);
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            if (event.getType() == Event.EventType.None && event.getPath() == null) {
                latch.countDown();
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    log.debug("重新获取节点列表：{}", zk.getChildren(event.getPath(), true));
                } catch (KeeperException | InterruptedException e) {
                    log.error("发生异常：{}", e.getMessage(), e);
                }
            }
        }
    }
}
