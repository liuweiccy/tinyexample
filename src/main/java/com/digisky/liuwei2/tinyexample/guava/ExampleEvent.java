package com.digisky.liuwei2.tinyexample.guava;

/**
 * 事件名
 *
 * @author Eric Liu
 * @date 2019/08/04 22:25
 */
public class ExampleEvent {
    private int num;

    public ExampleEvent(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
