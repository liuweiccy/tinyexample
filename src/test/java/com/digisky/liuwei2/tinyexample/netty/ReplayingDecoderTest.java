package com.digisky.liuwei2.tinyexample.netty;

import com.digisky.liuwei2.tinyexample.netty.codec.ToIntergerDecoder2;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author liuwei2
 */
public class ReplayingDecoderTest {

    @Test
    public void testReplayingDecoder() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 10; i++) {
            buf.writeInt(i);
        }

        EmbeddedChannel channel = new EmbeddedChannel(new ToIntergerDecoder2());

        assertTrue(channel.writeInbound(buf));
        assertTrue(channel.finish());

        for (int i = 0; i < 10; i++) {
            assertEquals(i, (int)channel.readInbound());
        }

        assertNull(channel.readInbound());
    }
}
