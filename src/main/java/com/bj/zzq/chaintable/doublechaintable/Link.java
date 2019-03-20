package com.bj.zzq.chaintable.doublechaintable;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/20
 * @Description:
 */
public class Link {
    private int iData;
    private double dData;
    private Link next;
    private Link prev;

    public Link() {

    }

    public Link(int iData, double dData) {
        this.iData = iData;
        this.dData = dData;
    }

    public int getiData() {
        return iData;
    }

    public void setiData(int iData) {
        this.iData = iData;
    }

    public double getdData() {
        return dData;
    }

    public void setdData(double dData) {
        this.dData = dData;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    public Link getPrev() {
        return prev;
    }

    public void setPrev(Link prev) {
        this.prev = prev;
    }

    public void displayLink() {
        System.out.println("{" + iData + "," + dData + "}");
    }
}
