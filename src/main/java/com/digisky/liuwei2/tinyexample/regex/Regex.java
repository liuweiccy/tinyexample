package com.digisky.liuwei2.tinyexample.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuwei2
 */
public class Regex {
    static void print(Object object) {
        System.out.println(object);
    }

    static void print(Object[] array) {
        System.out.println("-------------------------");
        for (Object obj : array) {
            System.out.println(obj);
        }
        System.out.println("-------------------------");
    }

    static void test1() {
        Pattern pattern = Pattern.compile("\\w+");
        print(pattern.pattern());
    }

    static void test2() {
        Pattern p = Pattern.compile("\\d+");
        String[] str = p.split("我的电话号码是：110我的工号是：1025我的邮箱是：liuwei2@digisky.com");
        print(str);
    }

    static void test3() {
        print(Pattern.matches("\\d+", "2233"));
        print(Pattern.matches("\\d+", "2233aa"));
        print(Pattern.matches("\\d+", "22aa33"));
        print(Pattern.matches("\\d+", "aa2233"));
    }

    static void test4() {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("22bb33");
        print(m.pattern().pattern());
    }

    static void test5() {
        Pattern p = Pattern.compile("\\d+");

        Matcher m = p.matcher("22bb33");
        print(m.matches());

        Matcher m1 = p.matcher("2233");
        print(m1.matches());
    }

    static void test6() {
        Pattern p = Pattern.compile("\\d+");

        Matcher m1 = p.matcher("22aa33");
        print(m1.lookingAt());

        Matcher m2 = p.matcher("aa2233");
        print(m2.lookingAt());
    }

    static void test7() {
        Pattern p = Pattern.compile("\\d+");

        Matcher m = p.matcher("22bb33");
        print(m.find());

        Matcher m1 = p.matcher("aabb");
        print(m1.find());
    }


    static void test8() {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("aa22bb33cc");

        print(m.find());
        print(m.start());
        print(m.end());
        print(m.group());

        Matcher m1 = p.matcher("2233bb");
        print(m1.matches());
        print(m1.start());
        print(m1.end());
        print(m1.group());
    }

    static void test9() {
        Pattern p = Pattern.compile("([a-z]+)(\\d+)");
        Matcher m = p.matcher("aaa2233bbb");

        print(m.find());
        print(m.groupCount());
        print(m.start(1));
        print(m.start(2));
        print(m.end(1));
        print(m.end(2));
        print(m.group(1));
        print(m.group(2));
    }

    static void test10() {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("我的电话号码是：110我的工号是：1025我的邮箱是：liuwei2@digisky.com");
        while (m.find()) {
            print(m.group());
            print(m.start());
            print(m.end());
        }
    }

    public static void main(String[] args) {
        Regex.test10();
    }
}
