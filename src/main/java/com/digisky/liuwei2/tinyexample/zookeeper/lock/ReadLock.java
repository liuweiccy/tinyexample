package com.digisky.liuwei2.tinyexample.zookeeper.lock;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;

/**
 * 自己实现的读锁
 * @author liuwei2
 */
class ReadLock implements Lock {
    private CuratorFramework client;
    /** 锁的路径 */
    private String path;
    /** 等待锁 */
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    /** 锁的状态(默认：解锁状态) */
    private LockStat stat = LockStat.UNLOCK;

    public ReadLock(CuratorFramework client, String path) {
        this.client = client;
        this.path = path;
    }

    @Override
    public void acquire() throws Exception {
        if (stat == LockStat.LOCKED) { return; }
        // 创建读节点
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
        // 锁住路径节点
        readLock();
    }

    private void readLock() throws Exception {
        String readNode = ZKPaths.getNodeFromPath(path);
        // 获取该读写锁节点下的所有子节点
        List<String> childrens = client.getChildren().forPath(path);
        String writeNode = getSmallerThemselvesButBiggestWriteNode(readNode, childrens);

        // 如果读节点前面没有写节点，加锁成功
        // 如果读节点前面存在写节点，那么，对该写节点注册监听器，当该节点释放时，进行加锁
        if (writeNode != null) {
            // 监听对应的节点，并进行处理
            process();

            stat = LockStat.TRY_LOCK;
            // 等待锁的释放
            countDownLatch.await();
        } else {
            stat = LockStat.LOCKED;
        }
    }

    private void process() {
        PathChildrenCache childrenCache = new PathChildrenCache(client, path, true);
        childrenCache.getListenable().addListener((client, event) -> {
            if (event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
                countDownLatch.countDown();
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
        if (isWrite(node)) {
            throw new IllegalArgumentException("该节点不是读节点：" + node);
        }

        // 节点排序
        nodes.sort((o1, o2) -> {
            int o1Seq = Integer.valueOf(o1.split("-")[1]);
            int o2Seq = Integer.valueOf(o2.split("-")[1]);
            return Integer.compare(o2Seq, o1Seq);
        });

        String writeNode = null;
        if (nodes != null && nodes.size() > 0) {
            for (String tempNode : nodes) {
                if (tempNode.equals(node)) { break; }
                if (isWrite(tempNode)) {
                    writeNode = tempNode;
                }
            }
        }
        return writeNode;
    }

    /**
     * 判断节点是否为读写节点
     * @param node  节点名字
     * @return      true,写节点；false,读节点
     */
    private boolean isWrite(String node) {
        if (node == null) { throw new NullPointerException("该节点的判断不能为空！"); }
        String[] nodeArray = node.split("-");
        switch (nodeArray[0]) {
            case "R":
                return false;
            case "W":
                return true;
            default:
                throw new IllegalArgumentException("该节点不是读写锁节点：" + node);
        }

    }

    @Override
    public void release() throws Exception {
        client.delete().deletingChildrenIfNeeded().forPath(path);
        stat = LockStat.UNLOCK;
    }
}
