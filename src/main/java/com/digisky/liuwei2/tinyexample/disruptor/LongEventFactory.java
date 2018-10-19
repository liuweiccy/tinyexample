package com.digisky.liuwei2.tinyexample.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author liuwei2
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}