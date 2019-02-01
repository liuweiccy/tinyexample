package com.digisky.liuwei2.tinyexample.netty.initchannel;

import com.digisky.liuwei2.tinyexample.proxy.proxytest.Person;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author liuwei2
 */
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private static final ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.debug("客户端收到消息:{}", msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        pool.scheduleAtFixedRate(() -> ctx.writeAndFlush(getPerson()), 1, 10, TimeUnit.SECONDS);
    }

    @NotNull
    private Person getPerson() {
        Person person = new Person();
        person.setId("1025");
        person.setName("vv");
        person.setBirthday(new Date());
        return person;
    }
}
