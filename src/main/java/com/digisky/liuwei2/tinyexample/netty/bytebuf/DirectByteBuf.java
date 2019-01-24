package com.digisky.liuwei2.tinyexample.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class DirectByteBuf {
    public static void main(String[] args) {
        ByteBuf directBuf = Unpooled.directBuffer(100);
        if (!directBuf.hasArray()) {
            directBuf.writeBytes("Hello Netty".getBytes(CharsetUtil.UTF_8));
            int length = directBuf.readableBytes();
            byte[] array = new byte[length];
            directBuf.getBytes(directBuf.readerIndex(), array);

            print(directBuf.capacity());
            print(directBuf.readerIndex() + ":" + length);
        }
    }
}
