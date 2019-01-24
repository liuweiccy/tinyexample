package com.digisky.liuwei2.tinyexample.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class ReferenceCount {

    public static void main(String[] args) {
        ByteBufAllocator allocator = new PooledByteBufAllocator(true);
        ByteBuf buf = allocator.directBuffer();
        print(buf.refCnt());
        print(buf.release());
        print(buf.release());
        print(buf.refCnt());
        print(buf.writeByte((byte)'a'));
        print(buf.refCnt());
    }

}
