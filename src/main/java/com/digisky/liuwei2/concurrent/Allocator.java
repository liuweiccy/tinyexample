package com.digisky.liuwei2.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuwei2
 * 2020/4/13 17:01
 */
public class Allocator {
    public static final Allocator instance = new Allocator();
    private List<Object> als = new ArrayList<>();

    private Allocator() {

    }

    synchronized boolean apply(Object from, Object to) {
        if (als.contains(from) || als.contains(to)) {
            return false;
        } else {
            als.add(from);
            als.add(to);
            return true;
        }
    }

    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
    }
}

class Account1 {
    private final Allocator allocator = Allocator.instance;
    private String name;
    private int balance;

    public Account1(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    void transfer(Account1 target, int amt) {
        while (!allocator.apply(this, target)) {}
        try {
            synchronized (this) {
                synchronized (target) {
                    if (this.balance >= amt) {
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        } finally {
            allocator.free(this, target);
        }
    }

    @Override
    public String toString() {
        return "Account1{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        Account1 a = new Account1("A", 200);
        Account1 b = new Account1("B", 200);
        Account1 c = new Account1("C", 200);

        Thread t1 = new Thread(() -> a.transfer(b, 100), "T1");
        Thread t2 = new Thread(() -> b.transfer(c, 100), "T2");
        Thread t3 = new Thread(() -> b.transfer(a, 100), "T2");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
