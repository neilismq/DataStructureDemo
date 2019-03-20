package com.bj.zzq.chaintable;

import com.bj.zzq.stack.StackWithArray;
import com.sun.deploy.net.cookie.CookieUnavailableException;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/20
 * @Description: 用单链表实现栈
 */
public class StackWithLinkedList {
    private SingleLinkedList linkedList;
    private int count;

    public StackWithLinkedList() {
        linkedList = new SingleLinkedList();
        count = 0;
    }

    public void push(Link link) {
        count++;
        linkedList.insertFirst(link.getiData(), link.getdData());
    }

    public Link pop() {
        if (isEmpty()) {
            throw new IllegalStateException("栈空");
        }
        count--;
        return linkedList.deleteFirst();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public int size() {
        return count;
    }

    public void display() {
        System.out.println("栈中内容：");
        this.linkedList.displayList();
    }

    public static void main(String[] args) {
        StackWithLinkedList stack = new StackWithLinkedList();
        stack.push(new Link(1, 2));
        stack.push(new Link(3, 4));
        stack.push(new Link(5, 6));
        stack.display();
        System.out.println();
        Link pop = stack.pop();
        stack.pop();

        stack.display();
    }
}
