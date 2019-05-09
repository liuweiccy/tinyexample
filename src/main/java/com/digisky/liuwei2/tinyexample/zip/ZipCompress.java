package com.digisky.liuwei2.tinyexample.zip;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author liuwei2
 */
public class ZipCompress {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("test.zip");
        CheckedOutputStream cos = new CheckedOutputStream(fos, new CRC32());
        ZipOutputStream zos = new ZipOutputStream(cos);
        BufferedOutputStream bos = new BufferedOutputStream(zos);

        zos.setComment("A test of Java Zipping");

        for (String arg : args) {
            System.out.println("Writing file: " + arg);
            BufferedReader in = new BufferedReader(new FileReader(arg));
            ZipEntry zipEntry = new ZipEntry(arg);
            zos.putNextEntry(zipEntry);
            int c;
            while ((c = in.read()) != -1) {
                bos.write(c);
            }
            in.close();
            bos.flush();
        }
        bos.close();

        System.out.println("CheckSum: " + cos.getChecksum().getValue());

        System.out.println("Reading file");
        FileInputStream fi = new FileInputStream("test.zip");
        CheckedInputStream cis = new CheckedInputStream(fi, new Adler32());
        ZipInputStream zis = new ZipInputStream(cis);
        BufferedInputStream bis = new BufferedInputStream(zis);

        ZipEntry ze;
        while ((ze = zis.getNextEntry()) != null) {
            System.out.println("Reading file " +  ze);
            int x;
            while ((x = bis.read()) != -1) {
//                System.out.write(x);
            }
        }

        if (args.length == 1) {
            System.out.println("Checksum: " + cis.getChecksum().getValue());
        }
        bis.close();
        ZipFile zf = new ZipFile("test.zip");
        Enumeration e = zf.entries();
        while (e.hasMoreElements()) {
            ZipEntry ze2 = (ZipEntry) e.nextElement();
            System.out.println("File: " + ze2);
        }



    }
}
