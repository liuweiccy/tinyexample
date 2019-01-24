package com.digisky.liuwei2.tinyexample.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class FindByte {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.wrappedBuffer("Hello Netty".getBytes(CharsetUtil.UTF_8));

        int index = buf.forEachByte(ByteProcessor.FIND_ASCII_SPACE);
        print(index);

        index = buf.forEachByte(0, 4, ByteProcessor.FIND_ASCII_SPACE);
        print(index);

        index = buf.forEachByte(new ByteProcessor.IndexOfProcessor((byte) 'e'));
        print(index);

        index = buf.forEachByteDesc(new ByteProcessor.IndexOfProcessor((byte) 'e'));
        print(index);

        index = buf.forEachByteDesc(new ByteProcessor.IndexNotOfProcessor((byte) 'e'));
        print(index);
    }
}
