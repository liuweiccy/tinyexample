package com.digisky.liuwei2.tinyexample.zookeeper.lock;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.curator.utils.ZKPaths;

/**
 * 锁的工具助手类
 * @author liuwei2
 * @date 2019/7/5
 */
class LockUtil {
    /** 读锁标志 */
    final static String READ = "R";
    /** 写锁标志 */
    final static String WRITE = "W";
    /** 减号分隔符 */
    final static String MINUS = "-";

    /**
     * 判断节点是否为读写节点
     * @param node  节点名字
     * @return      true,写节点；false,读节点
     */
    static boolean isWrite(String node) {
        if (node == null) { throw new NullPointerException("该节点的判断不能为空！"); }
        String[] nodeArray = node.split(MINUS);
        switch (nodeArray[0]) {
            case READ:
                return false;
            case WRITE:
                return true;
            default:
                throw new IllegalArgumentException("该节点不是读写锁节点：" + node);
        }
    }

    /**
     * 获取有序的路径
     * @param flag  读写锁的标志
     * @return      生成的有序锁的标志
     */
    static String seqPath(String path, String flag, AtomicLong counter) {
        return ZKPaths.makePath(path, flag + LockUtil.MINUS + counter.incrementAndGet());
    }
}
