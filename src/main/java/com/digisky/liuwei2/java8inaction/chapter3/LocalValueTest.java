package com.digisky.liuwei2.java8inaction.chapter3;

/**
 * lambda引用局部变量
 *
 * @author liuwei2
 * @date 2019/10/10 12:32
 */
public class LocalValueTest {
    public static void main(String[] args) {
        int port = 1234;
        int finalPort = port;
        // lambda 应用局部变量，必须为final或者事实为final（建议直接定义为final）
        Runnable r = () -> System.out.println(finalPort);
        port = 123;
    }
}
