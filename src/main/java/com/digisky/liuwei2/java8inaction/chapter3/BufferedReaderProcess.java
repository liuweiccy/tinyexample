package com.digisky.liuwei2.java8inaction.chapter3;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * BufferedReader的函数式接口
 *
 * @author liuwei2
 * @date 2019/10/09 18:22
 */
@FunctionalInterface
public interface BufferedReaderProcess {
    String process(BufferedReader br) throws IOException;
}
