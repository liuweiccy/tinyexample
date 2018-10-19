package com.digisky.liuwei2.tinyexample.concurrent.semaphore;

import java.util.Date;
import java.util.concurrent.Semaphore;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class PrintQueue {
    private Semaphore semaphore;

    public PrintQueue(int num) {
        this.semaphore = new Semaphore(num);
    }

    public void printing() {
        try {
            print(semaphore.toString());
            semaphore.acquire();
            Thread.sleep(3000);
            print(Thread.currentThread().getName() + " print done ... " + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
