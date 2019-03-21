package com.bj.zzq.chaintable.doublechaintable;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/21
 * @Description: 迭代器
 */
public class ListIterator {
    private Link current;
    private Link prev;
    private DoubleLinkedList list;

    public ListIterator(DoubleLinkedList list) {
        this.list = list;
        reset();
    }

    public void reset() {
        current = list.getFirst();
        prev = null;
    }

    public boolean hasNext() {
        return current.getNext() == null;
    }

    public Link next() {
        prev = current;
        current = current.getNext();
        return current;
    }

    //todo: insertAfer and insertBefore and deleteCurrent




}
