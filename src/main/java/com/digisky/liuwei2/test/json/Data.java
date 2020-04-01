package com.digisky.liuwei2.test.json;

/**
 * @author liuwei2
 * 2020/1/19 11:01
 */
public class Data {
    private int length;
    private String text;

    public void setLength(int length){
        this.length = length;
    }
    public int getLength(){
        return this.length;
    }
    public void setText(String text){
        this.text = text;
    }
    public String getText(){
        return this.text;
    }

    @Override
    public String toString() {
        return "Data{" +
                "length=" + length +
                ", text='" + text + '\'' +
                '}';
    }
}
