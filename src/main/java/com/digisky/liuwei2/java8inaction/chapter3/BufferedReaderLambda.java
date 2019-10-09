package com.digisky.liuwei2.java8inaction.chapter3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 一步步构建Lambda函数式接口
 *
 * @author liuwei2
 * @date 2019/10/09 18:15
 */
public class BufferedReaderLambda {

    /**
     * 对该方法进行lambda化改造
     */
    public static String process() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data2.txt"))) {
            return br.readLine();
        }
    }

    /**
     *  1,行为参数化
     *  2,使用函数式接口传递行为
     *  3,执行行为
     *  4,传递lambda表达式
     */
    public static String processLambda(BufferedReaderProcess brp) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data2.txt"))) {
            return brp.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
        process();

        processLambda((BufferedReader::readLine));
        processLambda((BufferedReader br) -> br.readLine() + br.readLine());
    }
}
