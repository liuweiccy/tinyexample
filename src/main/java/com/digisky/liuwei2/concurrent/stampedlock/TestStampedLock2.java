package com.digisky.liuwei2.concurrent.stampedlock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author liuwei2
 * 2020/4/27 11:09
 */
public class TestStampedLock2 {
    private double x,y;
    private final StampedLock sl = new StampedLock();

    // 写锁的应用
    void move(double newX, double newY) {
        long stamp = sl.writeLock();
        try {
            x = newX;
            y = newY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    // 乐观锁使用及其转换
    double distanceFromOrigin() {
        long stamp = sl.tryOptimisticRead();
        double currentX = x, currentY = y;

        if (!sl.validate(stamp)) {
            sl.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                sl.unlockRead(stamp);
            }
        }

        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    // 锁的转换
    void moveIfAtOrigin(double newX, double newY) {
        long stamp = sl.readLock();

        try {
            while (x == 0.0 && y == 0.0) {
                long ws = sl.tryConvertToWriteLock(stamp);
                if (ws != 0) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlock(stamp);
        }
    }
}
