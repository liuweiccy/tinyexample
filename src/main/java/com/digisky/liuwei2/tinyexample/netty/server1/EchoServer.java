package com.digisky.liuwei2.tinyexample.netty.server1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class EchoServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServer.class);
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        LOGGER.debug("可用的处理器数量: {}", Runtime.getRuntime().availableProcessors());
        new EchoServer(6985).start();
    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("logging", new LoggingHandler());
                            ch.pipeline().addLast("简单业务逻辑处理器", new EchoServerHandler());
                            ch.pipeline().addLast("简单业务逻辑处理器2", new EchoClientHandler());
                            ch.pipeline().addLast("timeout", new ReadTimeoutHandler(10));
//                            ch.pipeline().addLast("timeout", new WriteTimeoutHandler(10));
//                            ch.pipeline().addLast("timeout", new IdleStateHandler(10, 10, 10));
                            ch.pipeline().names();
                    }});

            ChannelFuture f = b.bind().sync();
            print("Server Starting......");
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
