package com.digisky.liuwei2.tinyexample.async;

/**
 * 回调的操作接口
 *
 * @author liuwei2
 */
public interface Handler<T> {

    T handler(Object msg);
}
