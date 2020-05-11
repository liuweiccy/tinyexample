package com.digisky.liuwei2.concurrent.designpattern.guardedsuspension;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author liuwei2
 * 2020/5/9 17:15
 */
public class TestGuardedObject {
    public static void main(String[] args) {
        final GuardedObject<Integer> go = new GuardedObject<>();
        Thread t1 = new Thread(() -> {
            // 模拟线程耗时
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "：设置值");
            go.onChange(100);
        });
        t1.start();

        // 主线程获取值
        Integer value = go.get(Objects::nonNull);
        System.out.println(Thread.currentThread().getName() + "：获取值："+ value);
    }
}
