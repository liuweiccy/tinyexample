package com.digisky.liuwei2.tinyexample.netty;

import com.digisky.liuwei2.tinyexample.proxy.proxytest.Person;
import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.marshalling.*;
import lombok.extern.slf4j.Slf4j;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author liuwei2
 */
@Slf4j
public class MarshallingCodecTest {
    @Test
    public void testMarshallingCodec() {
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        UnmarshallerProvider unmarshallerProvider = new DefaultUnmarshallerProvider(factory, configuration);
        MarshallerProvider marshallerProvider = new DefaultMarshallerProvider(factory, configuration);

        Person person = new Person();
        person.setId("1001");
        person.setName("vv");
        person.setBirthday(null);

        EmbeddedChannel channel = new EmbeddedChannel(new MarshallingDecoder(unmarshallerProvider), new MarshallingEncoder(marshallerProvider));

        assertTrue(channel.writeOutbound(person));
        ByteBuf buf = channel.readOutbound();

        channel.writeInbound(buf);
        Person person1 = channel.readInbound();
        assertEquals(person, person1);
    }
}
