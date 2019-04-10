package com.digisky.liuwei2.tinyexample.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liuwei2
 */
public class GetChannel {
    private static final int DBSIZE = 1024;

    public static void main(String[] args) throws IOException {
        FileChannel fc = new FileOutputStream("TextFile1.out").getChannel();
        fc.write(ByteBuffer.wrap("some text".getBytes()));
        fc.close();

        fc = new RandomAccessFile("TextFile1.out", "rw").getChannel();
        fc.position(fc.size());
        fc.write(ByteBuffer.wrap("some more".getBytes()));
        fc.close();

        fc = new FileInputStream("TextFile1.out").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(DBSIZE);
        fc.read(buffer);
        fc.close();
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
    }
}
