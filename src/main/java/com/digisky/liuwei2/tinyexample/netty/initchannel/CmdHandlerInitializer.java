package com.digisky.liuwei2.tinyexample.netty.initchannel;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.util.CharsetUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liuwei2
 */
@Slf4j
public class CmdHandlerInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast("decoder", new CmdDecoder(1024));
        ch.pipeline().addLast("handler", new CmdHandler());
    }


    @Getter
    @AllArgsConstructor
    public static final class Cmd {
        private ByteBuf name;
        private ByteBuf args;

        @Override
        public String toString() {
            return "Cmd{" +
                    "name=" + name.toString(CharsetUtil.UTF_8) +
                    ", args=" + args.toString(CharsetUtil.UTF_8) +
                    '}';
        }
    }

    public static final class CmdDecoder extends LineBasedFrameDecoder {
        final byte SPACE = ' ';

        public CmdDecoder(int maxLength) {
            super(maxLength);
        }

        @Override
        protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
            ByteBuf frame = (ByteBuf) super.decode(ctx, buffer);
            if (frame == null) { return null; }
            int index = frame.indexOf(frame.readerIndex(), frame.writerIndex(), SPACE);
            // 将消息解码为指定的对象
            return new Cmd(frame.readBytes(index), frame.readerIndex(index + 1).readBytes(frame.readableBytes()));
        }
    }

    public static final class CmdHandler extends SimpleChannelInboundHandler<Cmd> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Cmd msg) throws Exception {
            log.debug(msg.toString());
        }
    }
}
