package com.bj.zzq.chaintable;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/20
 * @Description: 单链表（只有一个表头first）
 */
public class SingleLinkedList {

    private Link first;

    /**
     * 在表头插入节点
     *
     * @param iData
     * @param dData
     */
    public void insertFirst(int iData, double dData) {
        Link link = new Link();
        link.setiData(iData);
        link.setdData(dData);
        link.setNext(first);
        first = link;
    }

    /**
     * 在指定节点后插入
     *
     * @param key
     * @return
     */
    public boolean insertAfter(int key, int iData, double dData) {
        Link current = first;
        while (current != null) {
            if (current.getiData() == key) {
                Link next = current.getNext();
                Link link = new Link();
                link.setiData(iData);
                link.setdData(dData);
                current.setNext(link);
                link.setNext(next);
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * 从表头删除节点
     *
     * @return
     */
    public Link deleteFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("链表为空，不能删除");
        }
        Link next = first.getNext();
        Link temp = first;
        first = next;
        return temp;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 找到指定节点
     *
     * @param key
     * @return
     */
    public Link find(int key) {
        Link node = first;
        while (node != null) {
            if (key == node.getiData()) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * 删除指定节点
     *
     * @param key
     * @return
     */
    public Link delete(int key) {
        Link current = first;
        Link prev = null;
        while (current != null) {
            if (key == current.getiData()) {
                if (current == first) {
                    first = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                return current;
            }
            prev = current;
            current = current.getNext();
        }
        return null;
    }

    /**
     * 展示所有节点
     */
    public void displayList() {
        Link node = first;
        while (node != null) {
            node.displayLink();
            node = node.getNext();
        }
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        list.insertFirst(1, 1.1);
        list.insertFirst(2, 2.1);
        list.insertFirst(3, 3.1);
        list.insertFirst(4, 4.1);
        list.displayList();


        System.out.println();
        list.insertAfter(7, 5, 6.5);
        System.out.println();
        list.displayList();


    }
}
