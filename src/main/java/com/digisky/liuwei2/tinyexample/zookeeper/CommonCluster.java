package com.digisky.liuwei2.tinyexample.zookeeper;

import java.io.IOException;

import org.apache.curator.test.TestingCluster;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 通用的3台zookeeper测试服务器集群
 * @author liuwei2
 */
@Slf4j
public class CommonCluster {
    @Getter
    private String connectString;
    private TestingCluster cluster;

    public void start() {
        cluster = new TestingCluster(3);
        try {
            cluster.start();
            connectString = cluster.getConnectString();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            stop();
        }
    }

    public void stop () {
        if (cluster != null) {
            try {
                cluster.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
