package com.digisky.liuwei2.leetcode;

import java.util.Arrays;
import java.util.Comparator;

import com.google.common.collect.Maps;

/**
 * 连续最长单词
 * TODO 最后一个测试用例失败
 *
 * @author Eric Liu
 * @date 2019/09/24 22:36
 */
public class LeetCode720 {
    public String longestWord(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        int index = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == 1) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return "";
        }

        String result = words[index];
        String temp = words[index];
        for (int i = index + 1; i < words.length; i++) {
            if (words[i].contains(temp) && words[i].length() - temp.length() == 1) {
                temp = words[i];
                if (temp.length() > result.length()) {
                    result = temp;
                }
            } else if (words[i].length() == 1) {
                temp = words[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[][] words = new String[][]{
                {"a", "banana", "app", "appl", "ap", "apply", "apple", "asdfsdgssssssss"},
                {"m","mo","moc","moch","mocha","l","la","lat","latt","latte","c","ca","cat"},
                {"yo","ew","fc","zrc","yodn","fcm","qm","qmo","fcmz","z","ewq","yod","ewqz","y"},
                {"k","lg","it","oidd","oid","oiddm","kfk","y","mw","kf","l","o","mwaqz","oi","ych","m","mwa"},
                {"w","wo","wor","worl","world"},
                {"rac","rs","ra","on","r","otif","o","onpdu","rsf","rs","ot","oti","racy","onpd"}
        };
        for (String[] w : words) {
            String ss = new LeetCode720().longestWord(w);
            System.out.println(ss);
        }
    }

}
