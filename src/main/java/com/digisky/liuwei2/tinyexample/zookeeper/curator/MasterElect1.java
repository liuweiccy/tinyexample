package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import lombok.extern.slf4j.Slf4j;

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
            .build();

    public static void main(String[] args) throws InterruptedException {
        client.start();

        new Thread(MasterElect1::masterElect, "测试线程1----").start();
        new Thread(MasterElect1::masterElect, "测试线程2----").start();
        new Thread(MasterElect1::masterElect, "测试线程3----").start();
        TimeUnit.SECONDS.sleep(100);
    }

    private static void masterElect() {
        LeaderSelector selector = new LeaderSelector(client, master_path, new LeaderSelectorListener() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                log.info(Thread.currentThread().getName() + " :成为Master角色");
                TimeUnit.SECONDS.sleep(1);
                log.info("完成Master操作，释放Master权力");
            }

            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                log.info("当前状态:{}", newState.toString());
            }
        });

        selector.autoRequeue();
        selector.start();
    }
}
