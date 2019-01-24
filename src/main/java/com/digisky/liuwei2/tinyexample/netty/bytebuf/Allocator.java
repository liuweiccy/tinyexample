package com.digisky.liuwei2.tinyexample.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class Allocator {
    public static void main(String[] args) {
        ByteBufAllocator allocator = new PooledByteBufAllocator(true);
        ByteBuf buf = allocator.buffer(10);
        print(buf.hasArray());

        buf = allocator.heapBuffer();
        print(buf.hasArray());

        buf = allocator.directBuffer();
        print(buf.hasArray());

        buf = allocator.compositeBuffer();
        print(buf.hasArray());
    }
}
