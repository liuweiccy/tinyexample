package com.digisky.liuwei2.tinyexample.netty.initchannel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author liuwei2
 */
public class HttpPipelineInitializer extends ChannelInitializer<SocketChannel> {
    private final boolean client;

    public HttpPipelineInitializer(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("logger", new LoggingHandler());
        if (client) {
            ch.pipeline().addLast("responseDecoder", new HttpResponseDecoder());
            ch.pipeline().addLast("requestEncoder", new HttpRequestEncoder());
        } else {
            ch.pipeline().addLast("requestDecoder", new HttpRequestDecoder());
            ch.pipeline().addLast("requestEncoder", new HttpRequestEncoder());
        }
    }
}
