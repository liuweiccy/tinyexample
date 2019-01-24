package com.digisky.liuwei2.tinyexample.io.practice18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author liuwei2
 */
public class Practice21 {

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String line;

        while ((line = buffer.readLine()) != null) {
            sb.append(line.toUpperCase()).append("\n");
        }

        System.out.println(sb.toString());
    }

}
