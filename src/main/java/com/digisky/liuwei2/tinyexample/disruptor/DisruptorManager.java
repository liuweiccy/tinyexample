package com.digisky.liuwei2.tinyexample.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class DisruptorManager {
    private Disruptor<LongEvent> disruptor;

    public void init() {
        EventFactory<LongEvent> eventFactory = new LongEventFactory();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        int ringBufferSize = 1024*1024;

        Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, ringBufferSize, executor, ProducerType.MULTI, new YieldingWaitStrategy());

        EventHandler<LongEvent> eventHandler = new LongEventHandler();
        disruptor.handleEventsWith(eventHandler);

        this.disruptor = disruptor;
    }

    public static void main(String[] args) {
        DisruptorManager manager = new DisruptorManager();
        manager.init();
        manager.disruptor.start();

        RingBuffer<LongEvent> ringBuffer = manager.disruptor.getRingBuffer();

        long s = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            long sequence = ringBuffer.next();

            LongEvent event = ringBuffer.get(sequence);
            event.setValue(1000L);
            // 推送新的数据
            ringBuffer.publish(sequence);
        }
        long e = System.currentTimeMillis();
        print("耗时： " + (e - s) + " ms");
    }
}
