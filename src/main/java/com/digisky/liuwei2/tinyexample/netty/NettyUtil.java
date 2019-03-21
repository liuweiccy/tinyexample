package com.digisky.liuwei2.tinyexample.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author liuwei2
 */
public class NettyUtil {
    public static EventLoopGroup getEventLoopGroup(int nThread) {
        return Epoll.isAvailable() ? new EpollEventLoopGroup(nThread) : new NioEventLoopGroup(nThread);
    }

    public static Class<? extends ServerChannel> getChannelClass() {
        return Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class;
    }
}
