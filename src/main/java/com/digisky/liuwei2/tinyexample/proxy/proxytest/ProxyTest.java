package com.digisky.liuwei2.tinyexample.proxy.proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @author liuwei2
 */
public class ProxyTest {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("VV");
        person.setId("CD1025");
        person.setBirthday(new Date());

        InvocationHandler handler = new SaveHandler(person);
        Save object = (Save) Proxy.newProxyInstance(handler.getClass().getClassLoader(), new Class[]{Save.class}, handler);
        object.persist();
    }
}
