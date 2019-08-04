package com.digisky.liuwei2.tinyexample.zookeeper.configcenter;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.digisky.liuwei2.tinyexample.util.Util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liuwei2
 */
@Slf4j
public class TestConfigCenter {
    private String connectString;
    private String path;

    @Before
    public void init() {
        connectString = "192.168.101.88:2183";
        path = "/configcenter/pubsub/test1";
    }

    @After
    public void destroy() {
    }

    @Test
    public void testServer() {
        for (String ip : connectString.split(",")) {
            Assert.assertTrue(Util.checkAddress(ip));
        }
    }

    @Ignore
    public void testClient() {
        try (CuratorFramework client = getClient()) {
            client.start();
            Stat stat = client.checkExists().forPath(path);
            if (stat == null) {
                client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
            }
            client.delete().deletingChildrenIfNeeded().forPath(path);
            TimeUnit.SECONDS.sleep(100);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testPubSub1() {

        ConfigCenter center1 = ConfigCenter.builder().client(getClient()).build();
        center1.sub(path, cache -> log.info("center1----获取到新的数据:{}", cache.getCurrentData().getData()));

        TopStudent student = new TopStudent("VV", 171, 2101111L);
        log.info("center1----发布数据：{}", student.toString());
        center1.pub(path, student.toByte());

        for (int i = 0; i < 10; i++) {
            student = new TopStudent("VV", 171, i);
            center1.pub(path, student.toByte());
        }
    }

    private CuratorFramework getClient() {
        return CuratorFrameworkFactory.builder()
                .retryPolicy(new RetryNTimes(5, 1000))
                .connectString(connectString)
                .build();
    }

}
