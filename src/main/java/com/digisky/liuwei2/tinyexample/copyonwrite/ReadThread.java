package com.digisky.liuwei2.tinyexample.copyonwrite;

import java.util.List;

/**
 * 读线程
 *
 * @author liuwei2
 */
public class ReadThread implements Runnable {
    private List<Integer> list;

    public ReadThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        list.forEach(System.out::println);
    }
}
