package com.digisky.liuwei2.tinyexample.copyonwrite;

import java.util.List;

/**
 * 写线程
 *
 * @author liuwei2
 */
public class WriteThread implements Runnable {
    private List<Integer> list;

    public WriteThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        list.add(1025);
    }
}
