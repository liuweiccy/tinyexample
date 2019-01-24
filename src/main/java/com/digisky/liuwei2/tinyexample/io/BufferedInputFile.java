package com.digisky.liuwei2.tinyexample.io;

import java.io.*;

/**
 * @author liuwei2
 */
public class BufferedInputFile {

    public static String read(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        try {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } finally {
            bufferedReader.close();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(read("c:/unintall.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
