package com.digisky.liuwei2.tinyexample.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author liuwei2
 */
public class OSExecute {
    public static void command(String command) throws IOException {
        String osType = System.getProperty("sun.desktop");
        if ("windows".equals(osType)) {
            command = "cmd /c " + command;
        }

        boolean err = false;

        Process process = new ProcessBuilder(command.split(" ")).start();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }

        BufferedReader br1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((s = br1.readLine()) != null) {
            System.out.println(s);
            err = true;
        }

        if (err) {
            throw new RuntimeException("Errors executing:" + command);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String command = bufferedReader.readLine();
        System.out.println("执行命令: " + command);
        OSExecute.command(command);
    }
}
