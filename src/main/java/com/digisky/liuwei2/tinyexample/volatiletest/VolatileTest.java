package com.digisky.liuwei2.tinyexample.volatiletest;

/**
 * @author liuwei2
 */
public class VolatileTest extends Thread {
    private volatile int a;

    @Override
    public void run() {
        final int c;
        c = a + 1;
        a = 1;
        System.out.println(c);
    }

}
