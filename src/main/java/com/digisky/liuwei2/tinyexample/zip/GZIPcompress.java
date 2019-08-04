package com.digisky.liuwei2.tinyexample.zip;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * @author liuwei2
 */
public class GZIPcompress {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dummy.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("test.gz")));

        int c ;
        while ((c = br.read()) != -1) {
            bos.write(c);
        }

        br.close();
        bos.close();

//        System.out.println("Reading file");
//        BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("test.gz"))));
//        String s;
//        while ((s = in2.readLine()) != null) {
//            System.out.println(s);
//        }

    }
}
