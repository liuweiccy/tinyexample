package com.digisky.liuwei2.tinyexample.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @author liuwei2
 */
public class IntBufferDemo {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        IntBuffer intBuffer = buffer.asIntBuffer();

        intBuffer.put(new int[]{11, 42, 47, 99, 143, 811, 1016});
        System.out.println(intBuffer.get(3));

        intBuffer.put(3, 1811);
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            int i = intBuffer.get();
            System.out.println(i);
        }

    }
}
