package com.digisky.liuwei2.tinyexample.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class ReaderIndex {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("Hello Netty".getBytes(CharsetUtil.UTF_8));
        buf.markReaderIndex();
        print((char)buf.readByte());
        print((char)buf.readByte());
        buf.resetReaderIndex();
        print((char)buf.readByte());
        print((char)buf.readByte());

        // buf.discardReadBytes();
        buf.clear();

        buf.markWriterIndex();
        buf.writeByte(97);
        buf.writeByte(98);
        print(buf.writerIndex());
        buf.resetWriterIndex();
        buf.writeByte(97);
        buf.writeByte(98);
        print(buf.writerIndex());
    }
}
