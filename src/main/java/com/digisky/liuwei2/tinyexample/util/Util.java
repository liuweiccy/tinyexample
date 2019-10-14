package com.digisky.liuwei2.tinyexample.util;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author liuwei2
 */
public class Util {
    public static void print(Object object) {
        if (object instanceof int[]) {
            System.out.println(Arrays.toString((int[]) object));
        } else {
            System.out.println(object);
        }
    }

    public static void print(Object[] array) {
        System.out.println("-------------------------");
        for (Object obj : array) {
            System.out.println(obj);
        }
        System.out.println("-------------------------");
    }

    public static boolean checkAddress(String address) {
        return Pattern.matches("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?):([0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-4]\\d{4}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5])", address);
    }
}
