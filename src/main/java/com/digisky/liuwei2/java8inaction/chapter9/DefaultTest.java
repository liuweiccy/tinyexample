package com.digisky.liuwei2.java8inaction.chapter9;

/**
 * @author liuwei2
 */
public interface DefaultTest {
    static void hello(String name) {
        System.out.println("Hello, " + name);
    }

    default void say(String name) {
        System.out.println("Say, " + name);
    }

    public static void main(String[] args) {
        DefaultTest.hello("VV");
        DefaultTest test = new DefaultTest() {};
        test.say("hello");
    }
}
