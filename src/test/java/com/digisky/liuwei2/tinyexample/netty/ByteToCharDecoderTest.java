package com.digisky.liuwei2.tinyexample.netty;

import com.digisky.liuwei2.tinyexample.netty.codec.ByteToCharDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liuwei2
 */
public class ByteToCharDecoderTest {
    @Test
    public void testByteToCharDecoder() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 10; i++) {
            buf.writeByte(i);
        }

        ByteBuf buf1 = buf.copy();

        EmbeddedChannel channel = new EmbeddedChannel(new ByteToCharDecoder());

        assertTrue(channel.writeInbound(buf));
        assertTrue(channel.finish());

        for (int i = 0; i < 5; i++) {
            assertEquals(buf1.readChar(), (char)channel.readInbound());
        }

        assertNull(channel.readInbound());
    }
}
