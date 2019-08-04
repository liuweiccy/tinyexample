package com.digisky.liuwei2.tinyexample.zookeeper.lock;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试自己基于zk的读写锁
 * @author liuwei2
 */
@Slf4j
public class MyReadWriteLockTest {
    private static String path = "/read_write_lock_path";
    private static CuratorFramework client;

    @Before
    public void init() {
        client = CuratorFrameworkFactory.builder()
                .connectString("192.168.101.88:2183")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .sessionTimeoutMs(3000)
                .build();
    }

    @Test
    public void testReadWriteLock() throws InterruptedException {
        client.start();

        MyReadWriteLock readWriteLock = new MyReadWriteLock(client, path);

        Lock readLock = readWriteLock.readLock();
        new Thread(() -> {
            try {
                log.info("R线程1准备获取锁");
                readLock.acquire();
                log.info("R线程1锁住");
                TimeUnit.SECONDS.sleep(1);
                readLock.release();
                log.info("R线程1释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Lock readLock2 = readWriteLock.readLock();
        new Thread(() -> {
            try {
                log.info("R线程2准备获取锁");
                readLock2.acquire();
                log.info("R线程2锁住");
                TimeUnit.SECONDS.sleep(1);
                readLock2.release();
                log.info("R线程2释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Lock writeLock = readWriteLock.writeLock();
        new Thread(() -> {
            try {
                log.info("W线程3准备获取锁");
                writeLock.acquire();
                log.info("W线程3锁住");
                TimeUnit.SECONDS.sleep(1);
                writeLock.release();
                log.info("W线程3释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Lock writeLock2 = readWriteLock.writeLock();
        new Thread(() -> {
            try {
                log.info("W线程4准备获取锁");
                writeLock2.acquire();
                log.info("W线程4锁住");
                TimeUnit.SECONDS.sleep(1);
                writeLock2.release();
                log.info("W线程4释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Lock readLock5 = readWriteLock.readLock();
        new Thread(() -> {
            try {
                log.info("R线程5准备获取锁");
                readLock5.acquire();
                log.info("R线程5锁住");
                TimeUnit.SECONDS.sleep(1);
                readLock5.release();
                log.info("R线程5释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Lock readLock6 = readWriteLock.readLock();
        new Thread(() -> {
            try {
                log.info("R线程6准备获取锁");
                readLock6.acquire();
                log.info("R线程6锁住");
                TimeUnit.SECONDS.sleep(1);
                readLock6.release();
                log.info("R线程6释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Lock writeLock7 = readWriteLock.writeLock();
        new Thread(() -> {
            try {
                log.info("W线程7准备获取锁");
                writeLock7.acquire();
                log.info("W线程7锁住");
                TimeUnit.SECONDS.sleep(1);
                writeLock7.release();
                log.info("W线程7释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Lock readLock8 = readWriteLock.readLock();
        new Thread(() -> {
            try {
                log.info("R线程8准备获取锁");
                readLock8.acquire();
                log.info("R线程8锁住");
                TimeUnit.SECONDS.sleep(1);
                readLock8.release();
                log.info("R线程8释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(100);
    }
}
