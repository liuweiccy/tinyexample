package com.digisky.liuwei2.tinyexample.netty.httpServer;

import com.digisky.liuwei2.tinyexample.netty.initchannel.HttpPipelineInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liuwei2
 */
public class HttpServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        ServerBootstrap b = new ServerBootstrap();

        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup(4);

        try {
            b.group(boss, worker);
            b.channel(NioServerSocketChannel.class);
            b.localAddress(port);
            b.childHandler(new HttpPipelineInitializer(false));
            ChannelFuture f = b.bind().sync();
            f.addListener((ChannelFutureListener) future -> LOGGER.info("服务器启动成功,port:{}", port));

            f.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully().sync();
            boss.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) throws Exception {
        new HttpServer(6985).start();
    }
}
