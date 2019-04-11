package com.digisky.liuwei2.tinyexample.zookeeper.configcenter;

/**
 * @author liuwei2
 */
public interface MyZooKeeperJson<T> {
    byte[] toByte();

    T toObject(byte[] bytes);
}
