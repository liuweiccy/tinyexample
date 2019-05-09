package com.digisky.liuwei2.tinyexample.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

/**
 * @author liuwei2
 */
public class ViewBuffers {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{0,0,0,0,0,0,0,'a'});
        bb.rewind();

        System.out.println("Byte Buffer");
        while (bb.hasRemaining()) {
            System.out.println(bb.position() + " --> " + bb.get());
        }
        bb.rewind();

        CharBuffer cb = bb.asCharBuffer();
        System.out.println("Char Buffer");
        while (cb.hasRemaining()) {
            System.out.println(cb.position() + " --> " + cb.get());
        }
        bb.rewind();

        FloatBuffer fb = bb.asFloatBuffer();
        System.out.println("Float Buffer");
        while (fb.hasRemaining()) {
            System.out.println(fb.position() + " --> " + fb.get());
        }
        bb.rewind();

        IntBuffer ib = bb.asIntBuffer();
        System.out.println("Int Buffer");
        while (ib.hasRemaining()) {
            System.out.println(ib.position() + " --> " + ib.get());
        }
        bb.rewind();

        LongBuffer lb = bb.asLongBuffer();
        System.out.println("Long Buffer");
        while (lb.hasRemaining()) {
            System.out.println(lb.position() + " --> " + lb.get());
        }
        bb.rewind();

        DoubleBuffer db = bb.asDoubleBuffer();
        System.out.println("Double Buffer");
        while (db.hasRemaining()) {
            System.out.println(db.position() + " --> " + db.get());
        }
        bb.rewind();

        ShortBuffer sb = bb.asShortBuffer();
        System.out.println("Short Buffer");
        while (sb.hasRemaining()) {
            System.out.println(sb.position() + " --> " + sb.get());
        }
        bb.rewind();
    }
}
