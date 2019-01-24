package com.digisky.liuwei2.tinyexample.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class HeapByteBuf {
    public static void main(String[] args) {
        ByteBuf heapBuf = Unpooled.copiedBuffer("Hello Netty", CharsetUtil.UTF_8);

        if (heapBuf.hasArray()) {
            byte[] array = heapBuf.array();
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            int length = heapBuf.readableBytes();
            print(array.length);
            print(offset + ":" + length);
        }
    }
}
