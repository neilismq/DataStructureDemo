package com.bj.zzq.chaintable.circular;

import com.bj.zzq.chaintable.Link;
import com.bj.zzq.chaintable.StackWithLinkedList;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/22
 * @Description: 由循环链表实现栈
 */
public class StackWithCircularList {
    private CircularLinkedList list;

    public StackWithCircularList() {
        list = new CircularLinkedList();
    }

    public Link pop() {
        return list.deleteNext();
    }

    public void push(Link link) {
        list.insertNext(link);
    }

    public Link peek() {
        return list.find(list.getCurrent().getNext().getiData());
    }

    public void display() {
        list.displayList();
    }

    public static void main(String[] args) {
        StackWithCircularList stack = new StackWithCircularList();
        stack.push(new Link(1, 1));
        stack.push(new Link(2, 1));
        stack.push(new Link(3, 1));
        stack.display();
        Link peek = stack.peek();
        peek.displayLink();

        stack.pop();
        stack.display();

        stack.push(new Link(4, 1));

        stack.display();

        stack.pop();

        stack.display();
    }

}
