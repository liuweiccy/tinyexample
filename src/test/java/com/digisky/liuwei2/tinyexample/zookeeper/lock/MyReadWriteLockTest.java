package com.digisky.liuwei2.tinyexample.zookeeper.lock;

import java.util.Random;
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
    private static String path = "/R_W_lock_path";
    private static CuratorFramework client;

    @Before
    public void init() {
        client = CuratorFrameworkFactory.builder()
                .connectString("192.168.101.88:2183")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
    }

    @Test
    public void testReadWriteLock() throws InterruptedException {
        client.start();

        MyReadWriteLock readWriteLock = new MyReadWriteLock(client, path);
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();

        TestData data = new TestData();
        int NUM = 30;

        // 读线程1
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

        // 读线程2
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

        // 写线程M
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

        // 写线程N
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
class TestData {
    private int data;

    int getData() throws InterruptedException {
        log.info("开始读取数据：{}", data);
        TimeUnit.MILLISECONDS.sleep(300);
        return data;
    }

    void setData(int data) throws InterruptedException {
        log.info("开始写入数据：{}", data);
        TimeUnit.SECONDS.sleep(1);
        this.data = data;
    }
}
