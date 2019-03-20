package com.bj.zzq.chaintable;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/20
 * @Description: 有序链表, 从表头开始从小到大
 */
public class SortedLink {

    private Link first;


    /**
     * 有序的插入
     *
     * @param
     * @return
     */
    public void insert(int iData, double dData) {
        Link node = new Link(iData, dData);
        if (first == null) {
            first = node;
            return;
        }
        Link current = first;
        Link prev = null;
        while (current != null) {
            if (iData <= current.getiData()) {
                if (prev == null) {
                    first = node;
                } else {
                    prev.setNext(node);
                }
                node.setNext(current);
                return;
            }
            prev = current;
            current = current.getNext();
        }
        prev.setNext(node);
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
        Link prev = first;
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
        System.out.println();
        Link node = first;
        while (node != null) {
            node.displayLink();
            node = node.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SortedLink list = new SortedLink();
        list.insert(20, 1.1);
        list.insert(40, 2.1);
        list.displayList();
        list.insert(10, 2.1);
        list.insert(30, 2.1);
        list.insert(50, 2.1);
        list.displayList();

        list.deleteFirst();
        list.displayList();


    }
}
