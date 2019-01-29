package com.digisky.liuwei2.tinyexample.netty;

import com.digisky.liuwei2.tinyexample.netty.codec.IntegerToStringDecoder;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liuwei2
 */
public class IntegerToStringDecoderTest {
    @Test
    public void testIntegerToStringDecoder() {
        EmbeddedChannel channel = new EmbeddedChannel(new IntegerToStringDecoder());
        int i = 0;
        while (i < 10) {
            i++;
            assertTrue(channel.writeInbound(i));
        }

        assertTrue(channel.finish());

        i = 0;
        while (i < 10) {
            i++;
            assertEquals(String.valueOf(i), channel.readInbound());
        }

        assertNull(channel.readInbound());
    }
}
