package com.digisky.liuwei2.tinyexample.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author liuwei2
 */
public class TraceHandler implements InvocationHandler {
    private Object target;

    public TraceHandler(Integer value) {
        target = value;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.printf("method:%s, args:%s \n", method.getName(), Arrays.toString(args));
        Object object = method.invoke(target, args);
        System.out.println(object);
        return object;
    }
}
