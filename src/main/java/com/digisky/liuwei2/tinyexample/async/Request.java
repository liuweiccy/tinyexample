package com.digisky.liuwei2.tinyexample.async;

import java.util.concurrent.TimeUnit;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class Request {

    public void request(String msg, Handler<String> handlerCallBack) {
        print(msg);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        handlerCallBack.handler(msg);
    }
}
