package com.digisky.liuwei2.tinyexample.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class CompositeByteBuf {
    public static void main(String[] args) {
        ByteBuf compositeBuf = Unpooled.compositeBuffer();
        compositeBuf.writeBytes("Hello Netty".getBytes(CharsetUtil.UTF_8));
        int cap = compositeBuf.capacity();
        int length = compositeBuf.readableBytes();
        int index = compositeBuf.readerIndex();

        print(cap);
        print(index + ":" + length);
        print(compositeBuf.hasArray());
    }
}
