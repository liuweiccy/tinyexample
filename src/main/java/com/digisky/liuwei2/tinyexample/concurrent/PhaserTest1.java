package com.digisky.liuwei2.tinyexample.concurrent;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class PhaserTest1 {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        phaser.register();
        phaser.bulkRegister(1);

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000);
                printInfo(phaser, 2);
                phaser.arrive();
                printInfo(phaser, 3);
                print(Thread.currentThread().getName() + " is done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.setName("子线程");
        thread.start();


        printInfo(phaser, 0);
        phaser.arrive();
        printInfo(phaser, 1);
        print(Thread.currentThread().getName() + " is done");

        try {
            phaser.awaitAdvanceInterruptibly(0, 1, TimeUnit.SECONDS);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }


        printInfo(phaser, "end");
    }


    private static <T> void printInfo(Phaser phaser, T flag) {
        int unarrived = phaser.getUnarrivedParties();
        int arrived = phaser.getArrivedParties();
        int registered= phaser.getRegisteredParties();
        print("   【" + flag + "】========注册：" + registered + " ||| 到达：" + arrived + " ||| 未到达：" + unarrived + " ||| 是否终止：" + phaser.isTerminated());
    }
}
