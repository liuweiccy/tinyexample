package com.digisky.liuwei2.tinyexample.util;

/**
 * @author liuwei2
 */
public class Util {
    public static void print(Object object) {
        System.out.println(object);
    }

    public static void print(Object[] array) {
        System.out.println("-------------------------");
        for (Object obj : array) {
            System.out.println(obj);
        }
        System.out.println("-------------------------");
    }
}
