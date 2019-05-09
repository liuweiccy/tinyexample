package com.digisky.liuwei2.tinyexample.netty.initchannel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author liuwei2
 */
public class HttpPipelineInitializer2 extends ChannelInitializer<SocketChannel> {
    private final boolean client;

    public HttpPipelineInitializer2(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("logger", new LoggingHandler());
        if (client) {
            ch.pipeline().addLast("clientCodec", new HttpClientCodec());
            ch.pipeline().addLast("decompress", new HttpContentDecompressor());
        } else {
            ch.pipeline().addLast("serverCodec", new HttpServerCodec());
            ch.pipeline().addLast("compress", new HttpContentCompressor());
        }

        // 聚合HTTP为完整的消息
        ch.pipeline().addLast(new HttpObjectAggregator(512*1024));
    }
}
