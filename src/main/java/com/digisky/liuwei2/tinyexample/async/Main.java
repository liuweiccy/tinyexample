package com.digisky.liuwei2.tinyexample.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class Main {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Request request = new Request();

        service.submit(() -> request.request("VV", msg -> {
            print("CallBack:" + msg);
            return (String) msg;
        }));

        service.submit(() -> request.request("VV", msg -> {
            print("CallBack:" + msg);
            return (String) msg;
        }));

        service.submit(() -> request.request("VV", msg -> {
            print("CallBack:" + msg);
            return (String) msg;
        }));

        service.submit(() -> request.request("VV", msg -> {
            print("CallBack:" + msg);
            return (String) msg;
        }));

        service.submit(() -> request.request("VV", msg -> {
            print("CallBack:" + msg);
            return (String) msg;
        }));

    }
}
