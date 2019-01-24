package com.digisky.liuwei2.tinyexample.io.practice18;

import com.digisky.liuwei2.tinyexample.io.BinaryFile;
import com.digisky.liuwei2.tinyexample.io.TextFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuwei2
 */
public class Practice19 {
    public static void main(String[] args) throws IOException {
        Map<Byte, Integer> countMap = new HashMap<>(1024);
        byte[] bytes = BinaryFile.read("c:unintall.log");
        for (byte b : bytes) {
            Integer count = countMap.get(b);
            if (count == null) {
                count = 0;
            }
            countMap.put(b, ++count);
        }
        System.out.println(countMap);

        Map<Character, Integer> countMap1 = new HashMap<>(1024);
        String text = TextFile.read("c:/unintall.log");
        for (char ch : text.toCharArray()) {
            Integer count = countMap1.get(ch);
            if (count == null) {
                count = 0;
            }
            countMap1.put(ch, ++count);

        }

        System.out.println(countMap1);
    }
}
