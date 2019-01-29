package com.digisky.liuwei2.tinyexample.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.util.CharsetUtil;
import org.junit.Test;

import static com.digisky.liuwei2.tinyexample.util.Util.print;
import static org.junit.Assert.*;

/**
 * @author liuwei2
 */
public class LineBasedFrameDecoderTest {

    @Test
    public void testLineBasedFrameDecoder() {
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes("Hello Java\n".getBytes());
        buf.writeBytes("Hello Netty\n".getBytes());
        buf.writeBytes("Hello Lua\n".getBytes());
        buf.writeBytes("Hello Go\n".getBytes());
        buf.writeBytes("Hello C++\n".getBytes());
        ByteBuf buf1 = buf.copy();

        EmbeddedChannel channel = new EmbeddedChannel(new LineBasedFrameDecoder(1024*4));

        assertTrue(channel.writeInbound(buf));
        assertTrue(channel.finish());

        ByteBuf read = channel.readInbound();
        print(read.toString(CharsetUtil.UTF_8));
        assertEquals(buf1.readBytes(10), read);
        buf1.skipBytes(1);
        read.release();

        read = channel.readInbound();
        print(read.toString(CharsetUtil.UTF_8));
        assertEquals(buf1.readBytes(11), read);
        buf1.skipBytes(1);
        read.release();

        read = channel.readInbound();
        print(read.toString(CharsetUtil.UTF_8));
        assertEquals(buf1.readBytes(9), read);
        buf1.skipBytes(1);
        read.release();

        read = channel.readInbound();
        print(read.toString(CharsetUtil.UTF_8));
        assertEquals(buf1.readBytes(8), read);
        buf1.skipBytes(1);
        read.release();

        read = channel.readInbound();
        print(read.toString(CharsetUtil.UTF_8));
        assertEquals(buf1.readBytes(9), read);
        buf1.skipBytes(1);
        read.release();

        assertNull(channel.readInbound());
        buf1.release();
    }
}
