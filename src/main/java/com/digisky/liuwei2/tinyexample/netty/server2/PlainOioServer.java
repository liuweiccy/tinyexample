package com.digisky.liuwei2.tinyexample.netty.server2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import io.netty.util.CharsetUtil;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class PlainOioServer {
    public void server(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        for (;;) {
            final Socket clientSocket = socket.accept();
            print("Accepted connection from " + clientSocket);
            new Thread(() -> {
                OutputStream out = null;
                try {
                    out = clientSocket.getOutputStream();
                    out.write("Hi\r\n".getBytes(CharsetUtil.UTF_8));
                    out.flush();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args) throws IOException {
        new PlainOioServer().server(6985);
    }
}
