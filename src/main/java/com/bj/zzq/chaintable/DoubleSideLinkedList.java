package com.bj.zzq.chaintable;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/20
 * @Description: 双端链表（表头指针first，表尾指针tail）
 */
public class DoubleSideLinkedList {
    private Link first;
    private Link tail;

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
        if (isEmpty()) {
            tail = link;
        }
        link.setNext(first);
        first = link;
    }

    public void insertTail(int iData, double dData) {
        Link link = new Link();
        link.setiData(iData);
        link.setdData(dData);
        if (isEmpty()) {
            first = link;
        } else {
            tail.setNext(link);
        }
        tail = link;
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
                if (next == null) {
                    tail = link;
                }
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
        Link temp=first;
        first = next;
        if (next == null) {
            tail = null;
        }
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
        Link prev = first;
        while (current != null) {
            if (key == current.getiData()) {
                if (current.getNext() == null) {
                    tail = null;
                }
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
        DoubleSideLinkedList list = new DoubleSideLinkedList();
        list.insertFirst(1, 1.1);
        list.insertFirst(2, 2.1);
        list.insertFirst(3, 3.1);
        list.insertFirst(4, 4.1);
        list.displayList();


        System.out.println();
        list.insertAfter(4, 5, 6.5);
        System.out.println();
        list.displayList();

        list.insertTail(8,8);

        System.out.println();
        list.displayList();




    }
}
