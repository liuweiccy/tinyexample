package com.digisky.liuwei2.tinyexample.zip;


import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

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
