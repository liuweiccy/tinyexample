package com.digisky.liuwei2.tinyexample.falsesharing;

import sun.misc.Contended;

/**
 * @author liuwei2
 */
public class FalseSharing implements Runnable {
    private final static int NUM_THREADS = 2;
    private final static long ITERATION = 2 << 26;
    private final int arrayIndex;
    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATION + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long s = System.currentTimeMillis();
        runTest();
        System.out.println(System.currentTimeMillis() - s);
    }

    @Contended
    private final static class VolatileLong {
        public long value = 0L;
    }
}
