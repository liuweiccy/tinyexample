package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import java.util.concurrent.TimeUnit;

import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingZooKeeperServer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liuwei2
 */
@Slf4j
public class TestingCluster1 {
    public static void main(String[] args) throws Exception {
        TestingCluster cluster = new TestingCluster(3);
        cluster.start();
        TimeUnit.SECONDS.sleep(2);

        TestingZooKeeperServer leader = null;

        for (TestingZooKeeperServer zs : cluster.getServers()) {
            log.error("集群状态：{} -- {} -- {}", zs.getInstanceSpec().getServerId(), zs.getQuorumPeer().getServerState()
                    , zs.getInstanceSpec().getDataDirectory().getAbsolutePath());

            if ("leading".equals(zs.getQuorumPeer().getServerState())) {
                leader = zs;
            }
        }

        if (leader != null) {
            leader.kill();
        }

        for (TestingZooKeeperServer zs : cluster.getServers()) {
            log.error("关闭leader后：{} -- {} -- {}", zs.getInstanceSpec().getServerId(), zs.getQuorumPeer().getServerState()
                    , zs.getInstanceSpec().getDataDirectory().getAbsolutePath());
        }

        if (leader != null) {
            leader.restart();
            TimeUnit.SECONDS.sleep(5);
        }

        for (TestingZooKeeperServer zs : cluster.getServers()) {
            log.error("重启集群后：{} -- {} -- {}", zs.getInstanceSpec().getServerId(), zs.getQuorumPeer().getServerState()
                    , zs.getInstanceSpec().getDataDirectory().getAbsolutePath());
        }

        TimeUnit.SECONDS.sleep(100);
        cluster.stop();
    }

}
