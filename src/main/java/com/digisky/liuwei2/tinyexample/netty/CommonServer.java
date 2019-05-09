package com.digisky.liuwei2.tinyexample.netty;

import com.digisky.liuwei2.tinyexample.netty.initchannel.ServerMarshallingInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liuwei2
 */
@Slf4j
public class CommonServer {
    public void start(int port) throws Exception {
        ServerBootstrap b = new ServerBootstrap();

        EventLoopGroup boss = NettyUtil.getEventLoopGroup(1);
        EventLoopGroup worker = NettyUtil.getEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2);

        try {
            b.group(boss, worker);
            b.channel(NettyUtil.getChannelClass());
            b.localAddress(port);

            b.childHandler(new ServerMarshallingInitializer());

            ChannelFuture f = b.bind().sync();
            f.addListener((ChannelFutureListener) future -> log.debug("服务器已经启动"));
            f.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new CommonServer().start(6985);
    }
}
