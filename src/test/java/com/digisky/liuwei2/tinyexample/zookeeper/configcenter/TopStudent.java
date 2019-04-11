package com.digisky.liuwei2.tinyexample.zookeeper.configcenter;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用于测试zookeeper，尖子生类
 * @author liuwei2
 */
@Data
@AllArgsConstructor
public class TopStudent implements Serializable, MyZooKeeperJson {
    private String name;
    private int age;
    private long sno;

    @Override
    public byte[] toByte() {
        return JSON.toJSONBytes(this, SerializerFeature.QuoteFieldNames);
    }

    @Override
    public TopStudent toObject(byte[] bytes) {
        return (TopStudent) JSONObject.parse(bytes, Feature.AllowUnQuotedFieldNames);
    }
}
