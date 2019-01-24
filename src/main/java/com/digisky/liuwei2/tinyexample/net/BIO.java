package com.digisky.liuwei2.tinyexample.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuwei2
 */
public class BIO {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6985);
        // 阻塞监听
        Socket socket = serverSocket.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String request, response;
        while ((request = in.readLine()) != null) {
            if ("Done".equals(request)) {
                break;
            }
            System.out.println("收到数据:" + request);
            response = "Server reply:" + request;
            out.write(response);
            out.flush();
        }

        out.close();
        in.close();
    }

}
