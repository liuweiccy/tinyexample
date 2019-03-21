package com.digisky.liuwei2.tinyexample.string;

import java.util.StringJoiner;

/**
 * @author liuwei2
 */
public class StringJoinerTest {
    public static void main(String[] args) {
        StringJoiner joiner1 = new StringJoiner(":", "[", "]");
        StringJoiner joiner2 = new StringJoiner("*", "(", ")");
        joiner1.add("VV");
        joiner1.add("YY");
        joiner1.add("XX");
        joiner2.add("AA").add("BB");
        joiner1.merge(joiner2);
        System.out.println(joiner1.length());
        System.out.println(joiner1.toString());
    }
}
