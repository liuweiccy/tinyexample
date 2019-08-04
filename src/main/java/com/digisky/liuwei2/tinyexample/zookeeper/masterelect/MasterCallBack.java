package com.digisky.liuwei2.tinyexample.zookeeper.masterelect;

import org.apache.curator.framework.CuratorFramework;

/**
 * Master选举回调
 * @author liuwei2
 */
public interface MasterCallBack {
    void takeMaster(CuratorFramework client, byte[] data, ElectStat electStat);
}
