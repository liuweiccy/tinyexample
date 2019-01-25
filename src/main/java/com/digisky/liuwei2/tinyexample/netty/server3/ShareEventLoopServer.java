package com.digisky.liuwei2.tinyexample.netty.server3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author liuwei2
 */
public class ShareEventLoopServer {
    public void server(int port) throws InterruptedException {
        ServerBootstrap b = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

        try {
            b.group(boss, worker);
            b.channel(NioServerSocketChannel.class);
            b.localAddress(port);
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ShareEventLoopHandler());
                }
            });

            ChannelFuture f = b.bind().sync();
            f.addListener((ChannelFutureListener) future -> System.out.println("Server Started"));
            f.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ShareEventLoopServer().server(6985);
    }
}
