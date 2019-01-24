package com.digisky.liuwei2.tinyexample.nio;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

/**
 * @author liuwei2
 */
public class DoubleBufferDemo {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        DoubleBuffer db = buffer.asDoubleBuffer();

        db.put(new double[]{11.0, 42.0, 47.0, 99.0, 143.0, 811.0, 1016.0});
        System.out.println(db.get(3));

        db.put(3, 1811);
        db.flip();

        while (db.hasRemaining()) {
            double i = db.get();
            System.out.println(i);
        }

    }
}
