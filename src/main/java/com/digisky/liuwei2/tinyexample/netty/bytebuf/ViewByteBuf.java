package com.digisky.liuwei2.tinyexample.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class ViewByteBuf {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("Hello Netty", CharsetUtil.UTF_8);
        ByteBuf buf1 = buf.duplicate();
        ByteBuf buf2 = buf.slice();
        ByteBuf buf3 = buf.copy();

        buf1.setByte(0, 'h');
        buf2.setByte(0, 'f');
        buf3.setByte(0, 'x');

        print(buf.toString(CharsetUtil.UTF_8));
        print(buf1.toString(CharsetUtil.UTF_8));
        print(buf2.toString(CharsetUtil.UTF_8));
        print(buf3.toString(CharsetUtil.UTF_8));
    }
}
