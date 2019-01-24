package com.digisky.liuwei2.tinyexample.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author liuwei2
 */
public class Echo {
    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(inputStreamReader);

        String s;
        while ((s=buffer.readLine())!= null && s.length() != 0) {
            System.out.println(s);
        }
    }
}
