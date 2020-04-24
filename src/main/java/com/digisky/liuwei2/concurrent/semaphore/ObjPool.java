package com.digisky.liuwei2.concurrent.semaphore;

import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author liuwei2
 * 2020/4/23 18:20
 */
public class ObjPool<T, R> {
    final List<T> pool;
    final Semaphore semaphore;

    public ObjPool(int size, T t) {
        this.pool = new Vector<>();
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        this.semaphore = new Semaphore(size, true);
    }

    R exec(Function<T, R> function) {
        T t = null;
        semaphore.acquireUninterruptibly();
        System.out.println(Thread.currentThread().getName() + ":" + semaphore.availablePermits());
        try {
            t = pool.remove(0);
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(10) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return function.apply(t);
        } finally {
            pool.add(t);
            semaphore.release();
        }
    }
}
