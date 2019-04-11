package com.digisky.liuwei2.tinyexample.zookeeper;

import java.io.IOException;

import org.apache.curator.test.TestingServer;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试用zookeeper
 * @author liuwei2
 */
@Slf4j
public class CommonZooKeeperServer {
    @Getter
    private String connectString;
    private TestingServer server;

    public void start() {
        try {
            server = new TestingServer();
            server.start();
            connectString = server.getConnectString();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            stop();
        }
    }

    public void stop () {
        if (server != null) {
            try {
                server.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
