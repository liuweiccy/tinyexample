package com.digisky.liuwei2.concurrent.atomic;

import java.util.Random;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author liuwei2
 * 2020/4/28 19:14
 */
public class TestAtomicMarkableReference {
    final AtomicMarkableReference<Point> markable = new AtomicMarkableReference<>(new Point(0), false);

    public static void main(String[] args) {
        final TestAtomicMarkableReference test = new TestAtomicMarkableReference();
        final Random random = new Random();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Point point = test.markable.getReference();
                System.out.println(point);
                boolean mark = test.markable.isMarked();
                test.markable.compareAndSet(point, new Point(random.nextInt(3)), mark, true);
            }).start();
        }
    }
}
