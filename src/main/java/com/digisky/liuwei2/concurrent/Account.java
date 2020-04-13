package com.digisky.liuwei2.concurrent;

/**
 * @author liuwei2
 * 2020/4/13 16:16
 */
public class Account {
    private Integer balance;
    private String password;

    void withdraw(Integer amt) {
        // this.balance这样的锁不具有意义，以为this.balance会发生变化
        synchronized (this.balance) {
            this.balance -= amt;
        }
    }

    void updatePassword(String pw) {
        synchronized (this.password) {
            this.password = pw;
        }
    }
}
