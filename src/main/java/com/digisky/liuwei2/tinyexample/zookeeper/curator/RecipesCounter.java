package com.digisky.liuwei2.tinyexample.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

import lombok.extern.slf4j.Slf4j;

/**
 * 分布式计数器示例
 * @author liuwei2
 */
@Slf4j
public class RecipesCounter {
    static String distatomicint_path = "/curator_recipes_distatomicint_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.101.88:2183")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) {
        client.start();

        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, distatomicint_path, new RetryNTimes(3, 1000));
        try {
            AtomicValue<Integer> value = atomicInteger.add(8);
            log.info("value:{}", value.postValue());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
