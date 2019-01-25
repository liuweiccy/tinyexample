package com.digisky.liuwei2.tinyexample.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liuwei2
 */
public class FixedLengthFrameDecoderTest {

    @Test
    public void testFramesDecoded() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i ++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();

        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));

        assertTrue(channel.writeInbound(input.retain()));
        assertTrue(channel.finish());

        ByteBuf read = channel.readInbound();
        System.out.println(read);
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        System.out.println(read);
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        System.out.println(read);
        assertEquals(buf.readSlice(3), read);
        read.release();

        assertNull(channel.readInbound());
        buf.release();
    }

    @Test
    public void testFramesDecoded2() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i ++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        assertFalse(channel.writeInbound(input.readBytes(2)));
        assertTrue(channel.writeInbound(input.readBytes(7)));
        assertTrue(channel.finish());

        ByteBuf read = channel.readInbound();
        System.out.println(read);
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        System.out.println(read);
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        System.out.println(read);
        assertEquals(buf.readSlice(3), read);
        read.release();

        assertNull(channel.readInbound());
        buf.release();

    }
}
