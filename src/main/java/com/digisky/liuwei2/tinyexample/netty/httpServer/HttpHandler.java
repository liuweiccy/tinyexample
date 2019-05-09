package com.digisky.liuwei2.tinyexample.netty.httpServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpResponse;

/**
 * @author liuwei2
 */
public class HttpHandler extends SimpleChannelInboundHandler<HttpMessage> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpHeaders.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpMessage msg) throws Exception {
        HttpHeaders headers = msg.headers();
        LOGGER.debug("header:{}", headers);
        HttpResponse response = new DefaultFullHttpResponse(null, null);
        ctx.writeAndFlush(response);
    }
}
