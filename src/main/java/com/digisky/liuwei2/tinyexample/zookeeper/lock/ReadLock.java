package com.digisky.liuwei2.tinyexample.zookeeper.lock;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;

import lombok.extern.slf4j.Slf4j;

/**
 * 自己实现的读锁
 * @author liuwei2
 */
@Slf4j
class ReadLock implements Lock {
    private CuratorFramework client;
    /** 锁的路径 */
    private String path;
    /** 节点路径 */
    private String pathNode;
    /** 等待锁时的阻塞器 */
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    /** 锁的状态(默认：解锁状态) */
    private LockStat stat = LockStat.UNLOCK;
    /** 序列号计数器 */
    private AtomicLong counter;

    public ReadLock(CuratorFramework client, String path, AtomicLong counter) {
        this.client = client;
        this.path = path;
        this.counter = counter;
    }

    @Override
    public void acquire() throws Exception {
        if (stat == LockStat.LOCKED) { return; }
        // 创建读节点
        pathNode = LockUtil.seqPath(path, LockUtil.READ, counter);
        log.info("创建读节点：{}", pathNode);
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(pathNode);
        // 锁住路径节点
        readLock();
    }

    private void readLock() throws Exception {
        ZKPaths.PathAndNode pathAndNode = ZKPaths.getPathAndNode(pathNode);
        String readNode = pathAndNode.getNode();
        // 获取该读写锁节点下的所有子节点
        List<String> childrens = client.getChildren().forPath(pathAndNode.getPath());
        String writeNode = getSmallerThemselvesButBiggestWriteNode(readNode, childrens);

        // 如果读节点前面没有写节点，加锁成功
        // 如果读节点前面存在写节点，那么，对该写节点注册监听器，当该节点释放时，进行加锁
        if (writeNode != null) {
            // 监听对应的节点，并进行处理
            process(ZKPaths.makePath(pathAndNode.getPath(), writeNode));

            stat = LockStat.TRY_LOCK;
            // 等待锁的释放
            countDownLatch.await();
            readLock();
        } else {
            stat = LockStat.LOCKED;
        }
    }

    /**
     * 对节点进行监听,等待该节点释放时，进一步进行操作
     * @param listenerPath  监听的对应节点
     */
    private void process(String listenerPath) throws Exception {
        PathChildrenCache pathCache = new PathChildrenCache(client, path, true);
        pathCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        pathCache.getListenable().addListener((client, event) -> {
            if (event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
                if (event.getData().getPath().equals(listenerPath)) {
                    countDownLatch.countDown();
                }
            }
        });
    }

    /**
     * 获取读节点上，比自己小的最大写节点
     * @param node  读节点本身
     * @param nodes 节点列表
     * @return  如果存在这样的写节点，则返回写节点；否则，返回<tt>null</tt>
     */
    private String getSmallerThemselvesButBiggestWriteNode(String node, List<String> nodes) {
        if (LockUtil.isWrite(node)) {
            throw new IllegalArgumentException("该节点不是读节点：" + node);
        }

        // 节点排序
        nodes.sort((o1, o2) -> {
            int o1Seq = Integer.valueOf(o1.split("-")[1]);
            int o2Seq = Integer.valueOf(o2.split("-")[1]);
            return Integer.compare(o1Seq, o2Seq);
        });

        String writeNode = null;
        if (nodes.size() > 0) {
            for (String tempNode : nodes) {
                if (tempNode.equals(node)) { break; }
                if (LockUtil.isWrite(tempNode)) {
                    writeNode = tempNode;
                }
            }
        }
        return writeNode;
    }

    @Override
    public void release() throws Exception {
        client.delete().deletingChildrenIfNeeded().forPath(pathNode);
        stat = LockStat.UNLOCK;
    }
}
