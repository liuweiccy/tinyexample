package com.digisky.liuwei2.tinyexample.zookeeper.masterelect;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liuwei2
 */
@Slf4j
public class TestMasterElect {
    private static CuratorFramework client;
    private static String path;

    @Before
    public void init() {
        client = CuratorFrameworkFactory.builder()
                .connectString("192.168.101.88:2183")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("master_elect")
                .sessionTimeoutMs(100)
                .connectionTimeoutMs(50)
                .build();

        path = "/test1";
    }

    @Test
    public void testConnected() {
        Assert.assertEquals(CuratorFrameworkState.LATENT, client.getState());
        client.start();
        Assert.assertEquals(CuratorFrameworkState.STARTED, client.getState());
        client.close();
        Assert.assertEquals(CuratorFrameworkState.STOPPED, client.getState());
    }

    @Test
    public void testPath1() {
        String absolutePath = ZKPaths.makePath(client.getNamespace(), path);
        log.info("完整的节点路径:{}", absolutePath);
        String nodePath = ZKPaths.getNodeFromPath(absolutePath);
        log.info("子节点名字:{}", nodePath);
        String parentPath = ZKPaths.getPathAndNode(absolutePath).getPath();
        log.info("父节点路径:{}", parentPath);
    }

    @Test
    public void testElect() throws Exception {
        client.start();
        MasterElect elect1 = new MasterElect(client, path, "master----1".getBytes(), new MasterCallBack() {
            @Override
            public void takeMaster(CuratorFramework client, byte[] data, ElectStat electStat) {
                if (electStat == ElectStat.MASTER) {
                    log.info("该节点111成为Master");
                } else {
                    log.info("当前节点111未入选Master,目前Master是:{}", new String(data));
                }
            }
        });

        MasterElect elect2 = new MasterElect(client, path, "master----2".getBytes(), new MasterCallBack() {
            @Override
            public void takeMaster(CuratorFramework client, byte[] data, ElectStat electStat) {
                if (electStat == ElectStat.MASTER) {
                    log.info("该节点222成为Master");
                } else {
                    log.info("当前节点222未入选Master,目前Master是:{}", new String(data));
                }
            }
        });

        MasterElect elect3 = new MasterElect(client, path, "master----3".getBytes(), new MasterCallBack() {
            @Override
            public void takeMaster(CuratorFramework client, byte[] data, ElectStat electStat) {
                if (electStat == ElectStat.MASTER) {
                    log.info("该节点333成为Master");
                } else {
                    log.info("当前节点333未入选Master,目前Master是:{}", new String(data));
                }
            }
        });

        new Thread(elect1::elect).start();
        new Thread(elect2::elect).start();
        new Thread(elect3::elect).start();
        TimeUnit.SECONDS.sleep(5);

        log.info("干掉Master,重新选举Master节点");
        try {
            client.delete().forPath(path);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        TimeUnit.SECONDS.sleep(100);
    }

}
