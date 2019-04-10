package com.digisky.liuwei2.tinyexample.netty;

import com.digisky.liuwei2.tinyexample.netty.initchannel.ClientMarshallingInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liuwei2
 */
@Slf4j
public class CommonClient {
    public void start(int port) throws Exception {
        Bootstrap b = new Bootstrap();

        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            b.group(worker);
            b.channel(NioSocketChannel.class);
            b.remoteAddress("127.0.0.1", port);
            b.handler(new ClientMarshallingInitializer());

            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        new CommonClient().start(6985);
    }
}
