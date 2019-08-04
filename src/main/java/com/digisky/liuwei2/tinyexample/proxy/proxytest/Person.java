package com.digisky.liuwei2.tinyexample.proxy.proxytest;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author liuwei2
 */
@Data
public class Person implements Save, Serializable {
    private String id;
    private String name;
    private Date birthday;

    @Override
    public void persist() {
        System.out.println("持久化完成......");
    }
}
