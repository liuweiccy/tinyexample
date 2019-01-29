package com.digisky.liuwei2.tinyexample.netty;

import com.digisky.liuwei2.tinyexample.netty.codec.ShortToByteEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liuwei2
 */
public class ShortToByteEncoderTest {
    @Test
    public void testShortToByteEncoder() {
        EmbeddedChannel channel = new EmbeddedChannel(new ShortToByteEncoder());

        for (short i = 0; i < 10; i++) {
            assertTrue(channel.writeOutbound(i));
        }
        assertTrue(channel.finish());

        for (int i = 0; i < 10; i++) {
            ByteBuf buf = channel.readOutbound();
            assertEquals((short)i, buf.readShort());
        }

        assertNull(channel.readOutbound());
    }
}
