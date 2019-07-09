package com.digisky.liuwei2.tinyexample.zookeeper.lock;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;

/**
 * 写锁实现
 * @author liuwei2
 * @date 2019/7/5
 */
public class WriteLock implements Lock {
    private CuratorFramework client;
    /** 锁的路径 */
    private String path;
    /** 等待锁时的阻塞器 */
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    /** 锁的状态(默认：解锁状态) */
    private LockStat stat = LockStat.UNLOCK;
    /** 序列号计数器 */
    private AtomicLong counter;

    public WriteLock(CuratorFramework client, String path, AtomicLong counter) {
        this.client = client;
        this.path = path;
        this.counter = counter;
    }

    @Override
    public void acquire() throws Exception {
        if (stat == LockStat.LOCKED) {
            return;
        }
        // 创建节点路径
        path = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(LockUtil.seqPath(path, LockUtil.WRITE, counter));
        // 创建写锁
        writeLock();
    }

    private void writeLock() throws Exception {
        ZKPaths.PathAndNode pathAndNode = ZKPaths.getPathAndNode(path);
        String writeNode = pathAndNode.getNode();
        // 获取该锁目录下的所有子节点
        List<String> childrens = client.getChildren().forPath(pathAndNode.getPath());

        String prevNode = getSmallerThemselvesNode(writeNode, childrens);

        // 如果写节点前面没有节点，加锁成功
        // 如果写节点前面存在节点，那么，对该节点注册监听器，当该节点释放时，进行加锁
        if (prevNode != null) {
            process(ZKPaths.makePath(pathAndNode.getPath(), prevNode));
            stat = LockStat.TRY_LOCK;
            countDownLatch.await();
        } else {
            stat = LockStat.LOCKED;
        }
    }

    /**
     * 监听节点，当节点被删除时，释放被阻塞的节点
     * @param listenerPath  被监听的节点
     */
    private void process(String listenerPath) {
        PathChildrenCache childrenCache = new PathChildrenCache(client, listenerPath, true);
        childrenCache.getListenable().addListener((client, event) -> {
            if (event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
               countDownLatch.countDown();
            }
        });
    }

    /**
     * 获取比写节点小的节点，最大节点
     * @param node      写节点本身
     * @param nodes     读节点
     * @return
     */
    private String getSmallerThemselvesNode(String node, List<String> nodes) {
        if (!LockUtil.isWrite(node)) {
            throw new IllegalArgumentException("该节点不是写节点：" + node);
        }

        // FIXME 存在非法节点:lock,可能bug产生，删除节点后重试,liuwei2,2019/7/10
        // 节点排序
        nodes.sort((o1, o2) -> {
            int o1Seq = Integer.valueOf(o1.split("-")[1]);
            int o2Seq = Integer.valueOf(o2.split("-")[1]);
            return Integer.compare(o2Seq, o1Seq);
        });

        String prevNode = null;
        if (!nodes.isEmpty()) {
            for (String tempNode : nodes) {
                if (tempNode.equals(node)) { break; }
                prevNode = tempNode;
            }
        }
        return prevNode;
    }

    @Override
    public void release() throws Exception {
        client.delete().deletingChildrenIfNeeded().forPath(path);
        stat = LockStat.UNLOCK;
    }
}
