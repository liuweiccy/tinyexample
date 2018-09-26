package com.digisky.liuwei2.tinyexample.generic;

/**
 * @author liuwei2
 */
public class PairTest {
    public static void main(String[] args) {
        Pair<String> pair = new Pair<>("VV", "YY");
        System.out.println(pair.toString());

        String[] a = new String[] {"liu", "wei", "chen", "cun", "yan", "yu", "xin"};

        System.out.println(ArrayAlg.minmax(a));
    }
}
