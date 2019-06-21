package com.digisky.liuwei2.tinyexample.zookeeper.lock;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;

import lombok.extern.slf4j.Slf4j;

/**
 * 自己实现读写锁
 * @author liuwei2
 */
@Slf4j
public class MyReadWriteLock1 {
    private CuratorFramework client;
    private String parent;
    private final String Read = "R";
    private final String Write = "W";
    private final String LINK = "-";
    private static final AtomicLong SEQ = new AtomicLong(0);

    public MyReadWriteLock1(CuratorFramework client, String path) {
        this.client = client;
        this.parent = path;
    }


    public String readLock() throws Exception {
        // 锁节点路径
        String path = ZKPaths.makePath(this.parent, Read + LINK + SEQ.getAndIncrement());
        // 创建读节点
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
        return readLockable(path);
    }

    private String readLockable(String path) throws Exception {
        String readNode = ZKPaths.getNodeFromPath(path);
        // 获取该读写锁节点下的所有子节点
        List<String> childrens = client.getChildren().forPath(this.parent);
        String writeNode = getSmallerThemselvesButBiggestWriteNode(readNode, childrens);

        // 如果读节点前面没有写节点，加锁成功
        // 如果读节点前面存在写节点，那么，对该写节点注册监听器，当该节点释放时，进行加锁
        if (writeNode != null) {
            PathChildrenCache childrenCache = new PathChildrenCache(client, ZKPaths.makePath(this.parent, writeNode), true);
            childrenCache.getListenable().addListener((client, event) -> {
                if (event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
                    readLockable(path);
                }
            });
            return null;
        } else {
            return path;
        }
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
     * 获取比写节点小的节点，最大节点
     * @param node      写节点本身
     * @param nodes     读节点
     * @return
     */
    private String getSmallerThemselvesNode(String node, List<String> nodes) {
        if (!isWrite(node)) {
            throw new IllegalArgumentException("该节点不是写节点：" + node);
        }
        String myNode = null;
        if (nodes != null && nodes.size() > 0) {
            for (String tempNode : nodes) {
                if (tempNode.equals(node)) { break; }
                myNode = tempNode;
            }
        }
        return myNode;
    }

    /**
     * 判断节点是否为读写节点
     * @param node  节点名字
     * @return      true,写节点；false,读节点
     */
    private boolean isWrite(String node) {
        if (node == null) { throw new NullPointerException("该节点的判断不能为空！"); }
        String[] nodeArray = node.split(LINK);
        switch (nodeArray[0]) {
            case Read:
                return false;
            case Write:
                return true;
            default:
                throw new IllegalArgumentException("该节点不是读写锁节点：" + node);
        }

    }

    public void unReadLock(String readNode) throws Exception {
        // 释放锁删除对应的节点
        client.delete().deletingChildrenIfNeeded().forPath(ZKPaths.makePath(parent, readNode));
    }

    public String writeLock() throws Exception {
        String path = ZKPaths.makePath(this.parent, Write + LINK + SEQ.getAndIncrement());
        // 创建写节点
        String myPath = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
        String writeNode = ZKPaths.getNodeFromPath(myPath);
        // 获取该锁目录下的所有子节点
        List<String> childrens = client.getChildren().forPath(this.parent);
        String myNode = getSmallerThemselvesNode(writeNode, childrens);

        // 如果写节点前面没有节点，加锁成功
        // 如果写节点前面存在节点，那么，对该节点注册监听器，当该节点释放时，进行加锁
        if (myNode != null) {
            PathChildrenCache childrenCache = new PathChildrenCache(client, this.parent, true);
            childrenCache.getListenable().addListener((client, event) -> {
                if (event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
                    // 加锁成功
                }
            });
        }
        return myPath;
    }

    public void unWriteLock(String writeNode) throws Exception {
        // 释放锁删除对应的节点
        client.delete().deletingChildrenIfNeeded().forPath(ZKPaths.makePath(parent, writeNode));
    }
}