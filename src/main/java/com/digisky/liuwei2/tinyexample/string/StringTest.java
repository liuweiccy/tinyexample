package com.digisky.liuwei2.tinyexample.string;

/**
 * 在实际的使用中我会调用String.equal方法，来做字符串的实际值判断
 *
 * @author liuwei2
 */
public class StringTest {
    public static void main(String[] args) {

        String str1 = "HelloFlyapi";

        String str2 = "HelloFlyapi";

        String str3 = new String("HelloFlyapi");

        String str4 = "Hello";

        String str5 = "Flyapi";

        String str6 = "Hello" + "Flyapi";

        String str7 = str4 + str5;

        // true
        System.out.println("str1 == str2 result: " + (str1 == str2));

        // false
        System.out.println("str1 == str3 result: " + (str1 == str3));

        // true
        System.out.println("str1 == str6 result: " + (str1 == str6));

        // false
        System.out.println("str1 == str7 result: " + (str1 == str7));

        // true
        System.out.println("str1 == str7.intern() result: " + (str1 == str7.intern()));

        // false
        System.out.println("str3 == str3.intern() result: " + (str3 == str3.intern()));

    }
}
