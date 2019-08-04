package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;

import lombok.extern.slf4j.Slf4j;

/**
 * 分布式锁示例
 * 应用框架所提供的读写锁
 * @author liuwei2
 */
@Slf4j
public class ReadWriteLock {
    static String lock_path = "/curator_read_write_lock_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.101.88:2183")
            .sessionTimeoutMs(3000)
            .connectionTimeoutMs(1000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws InterruptedException {
        client.start();

        final InterProcessReadWriteLock lock = new InterProcessReadWriteLock(client, lock_path);
        final InterProcessMutex readLock = lock.readLock();
        final InterProcessMutex writeLock = lock.writeLock();
        Data data = new Data();

        final int NUM = 30;
        // 读线程
        new Thread(() -> {
            for (int i = 0; i < NUM; i++) {
                try {
                    readLock.acquire();
                    log.info("1--读线程读取数据：{}", data.getData());
                    readLock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < NUM; i++) {
                try {
                    readLock.acquire();
                    log.info("2--读线程读取数据：{}", data.getData());
                    readLock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < NUM; i++) {
                try {
                    readLock.acquire();
                    log.info("3--读线程读取数据：{}", data.getData());
                    readLock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 写线程
        new Thread(() -> {
            for (int i = 0; i < NUM; i++) {
                try {
                    writeLock.acquire();
                    int v = new Random().nextInt(100);
                    data.setData(v);
                    log.info("M--写线程写入数据：{}", v);
                    writeLock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 写线程
        new Thread(() -> {
            for (int i = 0; i < NUM; i++) {
                try {
                    writeLock.acquire();
                    int v = new Random().nextInt(100);
                    data.setData(v);
                    log.info("N--写线程写入数据：{}", v);
                    writeLock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(10);
    }
}

@Slf4j
class Data {
    private int data;

    public int getData() throws InterruptedException {
        log.info("开始读取数据：{}", data);
        TimeUnit.MILLISECONDS.sleep(300);
        return data;
    }

    public void setData(int data) throws InterruptedException {
        log.info("开始写入数据：{}", data);
        TimeUnit.SECONDS.sleep(1);
        this.data = data;
    }
}
