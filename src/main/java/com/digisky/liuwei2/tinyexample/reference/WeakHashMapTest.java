package com.digisky.liuwei2.tinyexample.reference;

import java.util.Map;
import java.util.WeakHashMap;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * JDK WeakHashMap测试
 *
 * @author liuwei2
 */
public class WeakHashMapTest {
    private static Map<String, byte[]> caches = new WeakHashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i< 1000000; i++) {
            caches.put(i+"", new byte[1024*1024]);
            print("put num:" + i + " but caches size:" + caches.size());
        }
    }
}




