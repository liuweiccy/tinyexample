package com.digisky.liuwei2.tinyexample.guava;

import com.google.common.eventbus.Subscribe;

import java.util.concurrent.CountDownLatch;

/**
 * 事件监听器
 *
 * @author Eric Liu
 * @date 2019/08/04 22:30
 */
public class ExampleListener {
    private String msg;
    private CountDownLatch latch;

    public ExampleListener(CountDownLatch latch) {
        this.latch = latch;
    }

    @Subscribe
    public void listen(ExampleEvent event) {
        latch.countDown();
    }
}
