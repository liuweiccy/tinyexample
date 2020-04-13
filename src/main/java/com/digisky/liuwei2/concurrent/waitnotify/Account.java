package com.digisky.liuwei2.concurrent.waitnotify;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author liuwei2
 * 2020/4/13 18:29
 */
public class Account {
    private Allocator allocator = Allocator.Instance;
    private String name;
    private int balance;

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public void transfer(Account target, int amt) {
        allocator.replay(this, target);
        if (this.balance >= amt) {
            this.balance -= amt;
            target.balance += amt;
        }
        allocator.free(this, target);
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        Account a = new Account("A", 10000);
        Account b = new Account("B", 10000);
        CountDownLatch latch = new CountDownLatch(1000);
        for (int i=0; i< 500; i++) {
            new Thread(() -> {
                a.transfer(b, new Random().nextInt(100) + 1);
                latch.countDown();
            }).start();

            new Thread(() -> {
                b.transfer(a, new Random().nextInt(80) + 1);
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println(a);
        System.out.println(b);
    }
}

class Allocator {
    public static final Allocator Instance = new Allocator();
    private List<Object> als = new ArrayList<>();

    private Allocator() {}
    synchronized void replay(Object from, Object to) {
        while (als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        als.add(from);
        als.add(to);
    }

    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        notifyAll();
    }
}
