package com.digisky.liuwei2.tinyexample.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class CyclicBarrierTest1 {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(2, new Son());

        Thread thread = new Thread(() -> {
            try {
                printCB(cb, "1");
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } finally {
                printCB(cb, "2");
            }
        }
        );

        thread.start();



        Thread.sleep(1000);
        try {
            printCB(cb, "3");
            cb.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } finally {
            printCB(cb, "4");
        }

    }


    private static void printCB(CyclicBarrier cb, String flag) {
        print("----------------------" + flag + "--------------------------");
        print("isBroken:" + cb.isBroken());
        print("getNumberWaiting:" + cb.getNumberWaiting());
        print("");
        print("");
        print("");
        print("");
        print("");
    }
}


class Son implements Runnable {

    @Override
    public void run() {
        print("");
        print("");
        print("hello, my son!");
        print("");
        print("");
    }
}
