package com.digisky.liuwei2.tinyexample.netty.initchannel;

import com.digisky.liuwei2.tinyexample.proxy.proxytest.Person;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * @author liuwei2
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.debug("服务器收到信息:{}", msg.toString());
        Person person = getPerson();
        ctx.writeAndFlush(person);
    }

    @NotNull
    private Person getPerson() {
        Person person = new Person();
        person.setId("0001");
        person.setName("xin");
        person.setBirthday(new Date());
        return person;
    }
}
