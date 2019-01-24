package com.digisky.liuwei2.tinyexample.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class GetSetBuf {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", CharsetUtil.UTF_8);

        print((char)buf.getByte(0));
        print("readerIndex: " + buf.readerIndex());
        print("writerIndex: " + buf.writerIndex());

        buf.setByte(0, (byte)'B');

        print((char)buf.getByte(0));
        print("readerIndex: " + buf.readerIndex());
        print("writerIndex: " + buf.writerIndex());
    }
}
