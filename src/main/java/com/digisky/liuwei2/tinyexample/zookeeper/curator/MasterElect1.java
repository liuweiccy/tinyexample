package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * Master选举示例
 * @author liuwei2
 */
@Slf4j
public class MasterElect1 {
    static String master_path = "/curator_recipes_master_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.101.88:2183")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .sessionTimeoutMs(3000)
            .build();

    public static void main(String[] args) throws InterruptedException {
        client.start();

        LeaderSelector selector = new LeaderSelector(client, master_path, new LeaderSelectorListener() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                log.info("成为Master角色");
                TimeUnit.SECONDS.sleep(3);
                log.info("完成Master操作，释放Master权力");
            }

            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {

            }
        });

        selector.autoRequeue();
        selector.start();
        TimeUnit.SECONDS.sleep(100);
    }
}
