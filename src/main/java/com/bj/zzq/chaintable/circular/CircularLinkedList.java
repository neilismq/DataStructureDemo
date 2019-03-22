package com.bj.zzq.chaintable.circular;


import com.bj.zzq.chaintable.Link;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/22
 * @Description: 循环链表
 */
public class CircularLinkedList {
    private Link current;
    private Link prev;

    public boolean isEmpty() {
        return current == null;
    }

    public void insertNext(Link link) {
        if (isEmpty()) {
            //第一次插入，把当前节点的next设置为自身
            current = link;
            link.setNext(link);
        } else {
            link.setNext(current.getNext());
            current.setNext(link);
        }
    }


    public Link deleteNext() {
        if (isEmpty()) {
            throw new IllegalStateException("链表为空，不能删除");
        }
        Link temp = current.getNext();
        if (current.getNext() == current) {
            //链表中此时就一个元素
            current = null;
        } else {
            //多于1个元素
            current.setNext(current.getNext().getNext());
        }

        return temp;
    }

    public Link getCurrent() {
        return this.current;
    }

    public void step() {
        if (isEmpty()) {
            throw new IllegalStateException("链表为空，不能step");
        }
        prev = current;
        current = current.getNext();
    }

    public Link find(int key) {
        if (isEmpty()) {
            throw new IllegalStateException("链表为空，不能find");
        }
        Link now = current.getNext();
        Link temp = current;
        while (temp != now) {
            if (now.getiData() == key) {
                return now;
            }
            now = now.getNext();
        }
        //null代表没找到
        return null;
    }

    public void displayList() {
        if (isEmpty()) {
            System.out.println("链表为空");
        } else {
            Link temp = current;
            Link now = current;
            System.out.println();
            now = now.getNext();
            while (now != temp) {
                now.displayLink();
                now = now.getNext();
            }
            temp.displayLink();
            System.out.println();
        }
    }

}
