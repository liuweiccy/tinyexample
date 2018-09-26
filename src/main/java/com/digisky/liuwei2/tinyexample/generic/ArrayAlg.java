package com.digisky.liuwei2.tinyexample.generic;

/**
 * @author liuwei2
 */
public class ArrayAlg {

    public static <T extends Comparable<T>> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }

        T min = a[0];
        T max = a[0];

        for (int i = 0; i < a.length; i++) {
            T temp = a[i];
            if (temp.compareTo(min) < 0) { min = temp; }
            if (temp.compareTo(max) > 0) { max = temp; }
        }

        return new Pair<>(min, max);
    }
}
