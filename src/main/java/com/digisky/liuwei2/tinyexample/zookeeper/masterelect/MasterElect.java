package com.digisky.liuwei2.tinyexample.zookeeper.masterelect;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Master选举
 * @author liuwei2
 */
@Slf4j
@AllArgsConstructor
public class MasterElect {
    private CuratorFramework client;
    private String path;
    private byte[] data;
    private MasterCallBack callBack;

    @Override
    public String toString() {
        return "MasterElect{" +
                "client=" + client +
                ", path='" + path + '\'' +
                ", data=" + new String(data) +
                ", callBack=" + callBack +
                '}';
    }

    public void elect() {
        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, data);
            callBack.takeMaster(client, data, ElectStat.MASTER);
        } catch (Exception e) {
            // 节点已经存在
            if (e instanceof KeeperException.NodeExistsException) {
                // 回调
                try {
                    byte[] data1 = client.getData().forPath(path);
                    String oldValue = new String(data1);
                    String newValue = new String(data);
                    if (!newValue.equals(oldValue)) {
                        callBack.takeMaster(client, data1, ElectStat.SLAVE);
                    } else {
                        callBack.takeMaster(client, data1, ElectStat.MASTER);
                    }
                } catch (Exception ex) {
                    log.error(e.getMessage(), e);
                }
            } else {
                log.error(e.getMessage(), e);
            }
        } finally {
            // 当节点发生变化时通知所有的节点

        }
    }

    private void addListener() {
        // 监听节点数据的变化
        NodeCache cache = new NodeCache(client, path, false);
        cache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() {
                elect();
            }
        });

        try {
            cache.start(true);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
