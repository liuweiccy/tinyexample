package com.digisky.liuwei2.tinyexample.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * @author liuwei2
 */
public class UsingBuffers {
    private static void symmetricScramble(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            buffer.reset();
            buffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        char[] data = "UsingBuffers".toCharArray();
        ByteBuffer bb = ByteBuffer.allocateDirect(data.length * 2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);
        cb.rewind();
        System.out.println(cb);

        symmetricScramble(cb);
        cb.rewind();
        System.out.println(cb);

        symmetricScramble(cb);
        System.out.println(cb.rewind());

        System.out.println(System.currentTimeMillis() - s);
    }
}
