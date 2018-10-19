package com.digisky.liuwei2.tinyexample.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author liuwei2
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        // System.out.println("Event:" + event);
    }
}
