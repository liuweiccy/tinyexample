package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.ZooKeeper;

import lombok.extern.slf4j.Slf4j;

/**
 * zkpaths示例
 * @author liuwei2
 */
@Slf4j
public class ZKPaths1 {
    static String path = "/curator_zkpath_1";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.101.88:2183")
            .retryPolicy(new RetryNTimes(3, 1000))
            .build();

    public static void main(String[] args) {
        client.start();

        try {
            ZooKeeper zooKeeper = client.getZookeeperClient().getZooKeeper();
            log.info(ZKPaths.fixForNamespace("sub", path));
            log.info(ZKPaths.makePath(path, "sub"));
            log.info(ZKPaths.getNodeFromPath(path + "/sub1"));

            ZKPaths.PathAndNode pn = ZKPaths.getPathAndNode(path + "/sub1");
            log.info(pn.getPath());
            log.info(pn.getNode());

            String dir1 = path + "/child1";
            String dir2 = path + "/child2";

            ZKPaths.mkdirs(zooKeeper, dir1);
            ZKPaths.mkdirs(zooKeeper, dir2);

            log.info(String.valueOf(ZKPaths.getSortedChildren(zooKeeper, path)));
            ZKPaths.deleteChildren(client.getZookeeperClient().getZooKeeper(), path, true);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
