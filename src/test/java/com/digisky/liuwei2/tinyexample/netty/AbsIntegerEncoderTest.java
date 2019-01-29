package com.digisky.liuwei2.tinyexample.netty;

import com.digisky.liuwei2.tinyexample.netty.codec.AbsIntegerEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liuwei2
 */
public class AbsIntegerEncoderTest {

    @Test
    public void testAbsIntegerEncoder() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 1; i < 10; i++) {
            buf.writeInt(i * -1);
        }

        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
        assertTrue(channel.writeOutbound(buf));
        assertTrue(channel.finish());

        for (int i = 1; i < 10; i++) {
            assertEquals(i, (int)channel.readOutbound());
        }

        assertNull(channel.readOutbound());
    }
}
