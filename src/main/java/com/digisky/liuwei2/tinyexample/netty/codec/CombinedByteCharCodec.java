package com.digisky.liuwei2.tinyexample.netty.codec;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * @author liuwei2
 */
public class CombinedByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {
    public CombinedByteCharCodec() {
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
