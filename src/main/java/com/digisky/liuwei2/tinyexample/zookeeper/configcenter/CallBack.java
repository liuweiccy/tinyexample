package com.digisky.liuwei2.tinyexample.zookeeper.configcenter;

import org.apache.curator.framework.recipes.cache.NodeCache;

/**
 * 当订阅节点发生变化时，做回调处理
 * @author liuwei2
 */
public interface CallBack {
    /**
     * 回调处理
     * @param cache     节点缓存
     */
    void handle(NodeCache cache);
}
