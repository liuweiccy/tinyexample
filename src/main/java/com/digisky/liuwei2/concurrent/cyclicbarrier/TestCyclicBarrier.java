package com.digisky.liuwei2.concurrent.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author liuwei2
 * 2020/4/27 17:21
 */
public class TestCyclicBarrier {
    private final CyclicBarrier cb = new CyclicBarrier(3, this::all);
    private int salary, bonus;

    void getSalary() {
        int s = new Random().nextInt(10) + 1;
        try {
            TimeUnit.SECONDS.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        salary = 1000;
        System.out.println("计算完成基本工资，耗时：" + s);
    }

    void getBonus() {
        int s = new Random().nextInt(10) + 1;
        try {
            TimeUnit.SECONDS.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bonus = 300;
        System.out.println("计算完成奖金，耗时：" + s);
    }

    void all() {
        System.out.println("总的收入为：" + (salary + bonus));
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        long s = System.currentTimeMillis();
        final TestCyclicBarrier tcb = new TestCyclicBarrier();
        Thread t1 = new Thread(() -> {
            tcb.getSalary();
            try {
                tcb.cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            tcb.getBonus();
            try {
                tcb.cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t2.start();

        tcb.cb.await();
        long end = System.currentTimeMillis();
        System.out.println("总耗时（ms）：" + (end - s));

        System.out.println(tcb.cb.getNumberWaiting());
        System.out.println(tcb.cb.getParties());
    }
}
