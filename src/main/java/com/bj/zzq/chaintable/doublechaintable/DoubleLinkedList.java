package com.bj.zzq.chaintable.doublechaintable;

import com.bj.zzq.chaintable.doublechaintable.Link;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/20
 * @Description: 双向链表
 */
public class DoubleLinkedList {
    private Link first;
    private Link last;

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
            last = link;
        } else {
            first.setPrev(link);
        }
        link.setNext(first);
        first = link;
    }

    public void insertLast(int iData, double dData) {
        Link link = new Link();
        link.setiData(iData);
        link.setdData(dData);
        if (isEmpty()) {
            first = link;
        } else {
            last.setNext(link);
        }
        link.setPrev(last);
        last = link;
    }

    public boolean insertAfter(int key, int iData, double dData) {
        Link link = new Link();
        link.setiData(iData);
        link.setdData(dData);
        Link current = first;
        while (current != null) {
            if (current.getiData() == key) {
                Link next = current.getNext();
                current.setNext(link);
                link.setNext(next);
                link.setPrev(current);
                if (next != null) {
                    next.setPrev(link);
                } else {
                    last = link;
                }
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean insertBefore(int key, int iData, double dData) {
        Link link = new Link();
        link.setiData(iData);
        link.setdData(dData);
        Link current = first;
        while (current != null) {
            if (current.getiData() == key) {
                Link prev = current.getPrev();
                link.setNext(current);
                current.setPrev(link);
                link.setPrev(prev);
                if (prev != null) {
                    prev.setNext(link);
                } else {
                    first = link;
                }
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

        if (next == null) {
            last = null;
        } else {
            next.setPrev(null);
        }
        return temp;
    }

    public Link deleteLast() {
        if (isEmpty()) {
            throw new IllegalStateException("链表为空，不能删除");
        }
        Link temp = last;
        Link prev = last.getPrev();
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.setNext(null);
        }

        return temp;
    }

    public Link delete(int key) {
        Link current = first;
        while (current != null) {
            if (current.getiData() == key) {
                Link prev = current.getPrev();
                Link next = current.getNext();
                if (prev != null) {
                    prev.setNext(next);
                } else {
                    first = next;
                }
                if (next != null) {
                    next.setPrev(prev);
                } else {
                    last = prev;
                }
                current.setPrev(null);
                current.setNext(null);
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return first == null;
    }

    public void displayForward() {
        System.out.println("由前向后遍历：");
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.getNext();
        }
        System.out.println();
    }

    public void displayBackward() {
        System.out.println("由后向前遍历：");
        Link current = last;
        while (current != null) {
            current.displayLink();
            current = current.getPrev();
        }
        System.out.println();
    }

    public Link getFirst() {
        return first;
    }

    public void setFirst(Link first) {
        this.first = first;
    }

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        list.insertFirst(22,22);
        list.insertFirst(44,44);
        list.insertFirst(66,66);

        list.insertLast(11,11);
        list.insertLast(33,33);
        list.insertLast(55,55);

        list.displayForward();
        list.displayBackward();

        list.deleteFirst();
        list.deleteLast();
        list.delete(11);

        list.displayForward();

        list.insertAfter(22,77,77);
        list.insertAfter(33,77,77);

        list.displayForward();

    }
}
