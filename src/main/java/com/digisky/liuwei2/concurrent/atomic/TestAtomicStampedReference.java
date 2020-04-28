package com.digisky.liuwei2.concurrent.atomic;

import java.util.Random;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 测试AtomicStampedReference，解决ABA的问题因为每个reference都会带一个stamp
 *
 * @author liuwei2
 * 2020/4/28 18:49
 */
public class TestAtomicStampedReference {
    private final AtomicStampedReference<Point> stampedReference = new AtomicStampedReference<>(new Point(0), 1);

    public static void main(String[] args) {
        final TestAtomicStampedReference test = new TestAtomicStampedReference();
        final Random random = new Random();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Point point = test.stampedReference.getReference();
                System.out.println(point);
                int stamp = test.stampedReference.getStamp();
                test.stampedReference.compareAndSet(point, new Point(random.nextInt(3)), stamp, stamp + 1);
            }).start();
        }
    }
}
