package com.digisky.liuwei2.tinyexample.zookeeper.lock;

/**
 * 锁的状态
 * @author liuwei2
 */
public enum LockStat {
    /** 已加锁 */
    LOCKED,
    /** 解锁 */
    UNLOCK,
    /** 等待加锁 */
    TRY_LOCK
}
