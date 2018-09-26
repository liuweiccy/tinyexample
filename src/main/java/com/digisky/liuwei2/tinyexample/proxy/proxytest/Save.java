package com.digisky.liuwei2.tinyexample.proxy.proxytest;

/**
 * @author liuwei2
 */
public interface Save {
    @Log(format = "持久化实体:%s\n")
    void persist();
}
