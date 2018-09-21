package com.digisky.liuwei2.tinyexample.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * JDK Reference测试
 *
 * @author liuwei2
 */
public class ReferenceTest {
    private static List<Reference> roots = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue rq = new ReferenceQueue();

        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    Reference r = rq.remove();
                    print("reference:" + r);
                    print("get:" + r.get());
                    i++;
                    print("queue remove num:" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 0; i < 10000; i++) {
            byte[] b = new byte[1024*1024];
            Reference r = new SoftReference(b, rq);
            roots.add(r);
            System.gc();

            print("produce:" + i);
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
