package com.digisky.liuwei2.tinyexample.zookeeper.lock;

/**
 * 锁接口
 * @author liuwei2
 */
public interface Lock {
    /**
     * 获取锁
     * @exception Exception 在锁的过程中发生异常
     */
    void acquire() throws Exception;

    /**
     * 释放锁
     * @exception Exception 在释放锁的过程中发生的异常
     */
    void release() throws Exception;
}
