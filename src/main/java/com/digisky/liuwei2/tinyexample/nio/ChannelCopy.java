package com.digisky.liuwei2.tinyexample.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liuwei2
 */
public class ChannelCopy {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        FileChannel in = new FileInputStream("pom.xml").getChannel(),
                   out = new FileOutputStream("pom.xml.copy").getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);

        while (in.read(buffer) != -1) {
            buffer.flip();
            out.write(buffer);
            buffer.clear();
        }

        in.transferTo(0, in.size(), out);

        in.close();
        out.close();
    }
}
