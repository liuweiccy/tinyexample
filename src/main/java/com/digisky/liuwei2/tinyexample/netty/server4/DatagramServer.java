package com.digisky.liuwei2.tinyexample.netty.server4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class DatagramServer {
    public static void main(String[] args) {
        new DatagramServer().start();
    }

    public void start() {
        Bootstrap b = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

        b.group(group);
        b.channel(NioDatagramChannel.class);
        b.handler(new DatagramHandler());

        ChannelFuture f = b.bind(new InetSocketAddress(6985));
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    print("Channel bound");
                } else {
                    print("Bind attempt failed");
                }
            }
        });
    }
}
