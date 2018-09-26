package com.digisky.liuwei2.tinyexample.proxy.proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liuwei2
 */
public class SaveHandler implements InvocationHandler {
    private Object proxy;

    public SaveHandler(final Object proxy) {
        this.proxy = proxy;
    }

    @Override
    public Object invoke(Object p, Method method, Object[] args) throws Throwable {
        Log log = method.getDeclaredAnnotation(Log.class);
        if (log != null) {
            System.out.printf(log.format(), proxy.toString());
            return method.invoke(proxy, args);
        }
        return null;
    }
}
