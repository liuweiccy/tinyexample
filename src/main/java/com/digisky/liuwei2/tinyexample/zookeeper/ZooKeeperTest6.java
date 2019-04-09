package com.digisky.liuwei2.tinyexample.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *
 * 使用异步的API子节点
 * @author liuwei2
 */
@Slf4j
public class ZooKeeperTest6 implements Watcher {
    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zk = null;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        String path = "/zk-book-6";

        zk = new ZooKeeper("192.168.101.88:2183", 5000, new ZooKeeperTest6());
        latch.await();

        zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zk.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        zk.getChildren(path, true, new IChildren2Callback(), "第一次获取子节点回调ctx");

        zk.create(path + "/c2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        TimeUnit.SECONDS.sleep(100);
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            if (event.getType() == Event.EventType.None && event.getPath() == null) {
                latch.countDown();
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    log.debug("重新获取子节点：{}", zk.getChildren(event.getPath(), true));
                } catch (KeeperException | InterruptedException e) {
                    log.error("异常信息：{}", e.getMessage(), e);
                }
            }
        }
    }
}

@Slf4j
class IChildren2Callback implements AsyncCallback.Children2Callback {
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        log.debug("返回子节点结果：rc:{}，path:{}，ctx:{}，children:{}，stat:{}", rc, path, ctx, children, stat);
    }
}
