package com.digisky.liuwei2.test.json;

import java.util.List;

/**
 * @author liuwei2
 * 2020/1/19 11:05
 */
public class Root {
    private int minPosition;

    private boolean hasMoreItems;

    private String itemsHtml;

    private int newLatentCount;

    private Data data;

    private List<Integer> numericalArray ;

    private List<String> stringArray ;

    private int multipleTypesArray;

    private List<ObjArray> objArray ;

    private List<ObjArray> objArray2 ;

    public int getMinPosition() {
        return minPosition;
    }

    public void setMinPosition(int minPosition) {
        this.minPosition = minPosition;
    }

    public boolean isHasMoreItems() {
        return hasMoreItems;
    }

    public void setHasMoreItems(boolean hasMoreItems) {
        this.hasMoreItems = hasMoreItems;
    }

    public String getItemsHtml() {
        return itemsHtml;
    }

    public void setItemsHtml(String itemsHtml) {
        this.itemsHtml = itemsHtml;
    }

    public int getNewLatentCount() {
        return newLatentCount;
    }

    public void setNewLatentCount(int newLatentCount) {
        this.newLatentCount = newLatentCount;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Integer> getNumericalArray() {
        return numericalArray;
    }

    public void setNumericalArray(List<Integer> numericalArray) {
        this.numericalArray = numericalArray;
    }

    public List<String> getStringArray() {
        return stringArray;
    }

    public void setStringArray(List<String> stringArray) {
        this.stringArray = stringArray;
    }

    public int getMultipleTypesArray() {
        return multipleTypesArray;
    }

    public void setMultipleTypesArray(int multipleTypesArray) {
        this.multipleTypesArray = multipleTypesArray;
    }

    public List<ObjArray> getObjArray() {
        return objArray;
    }

    public void setObjArray(List<ObjArray> objArray) {
        this.objArray = objArray;
    }

    public List<ObjArray> getObjArray2() {
        return objArray2;
    }

    public void setObjArray2(List<ObjArray> objArray2) {
        this.objArray2 = objArray2;
    }

    @Override
    public String toString() {
        return "Root{" +
                "minPosition=" + minPosition +
                ", hasMoreItems=" + hasMoreItems +
                ", itemsHtml='" + itemsHtml + '\'' +
                ", newLatentCount=" + newLatentCount +
                ", data=" + data +
                ", numericalArray=" + numericalArray +
                ", stringArray=" + stringArray +
                ", multipleTypesArray=" + multipleTypesArray +
                ", objArray=" + objArray +
                ", objArray2=" + objArray2 +
                '}';
    }
}
