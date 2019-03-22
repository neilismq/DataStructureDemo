package com.bj.zzq.chaintable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/21
 * @Description: 迭代器
 */
public class ListIterator {
    private Link current;
    private Link prev;
    private SingleLinkedList list;

    public ListIterator(SingleLinkedList list) {
        this.list = list;
        reset();
    }

    public void reset() {
        current = list.getFirst();
        prev = null;
    }

    public boolean atEnd() {
        return current.getNext() == null;
    }

    public void next() {
        prev = current;
        current = current.getNext();

    }

    public Link getCurrent() {
        return this.current;
    }


    public void insertAfter(Link link) {
        if (list.isEmpty()) {
            list.insertFirst(link.getiData(), link.getdData());
            reset();
        } else {
            link.setNext(current.getNext());
            current.setNext(link);
            next();
        }
    }

    public void insertBefore(Link link) {
        if (prev == null) {
            link.setNext(list.getFirst());
            list.setFirst(link);
            reset();
        } else {
            link.setNext(prev.getNext());
            prev.setNext(link);
            current = link;
        }
    }

    public Link deleteCurrent() {
        if (list.isEmpty()) {
            throw new IllegalStateException("空链表，不能删除");
        }
        Link temp = current;
        if (prev != null) {
            prev.setNext(current.getNext());
            if (atEnd()) {
                reset();
            }
            next();
        } else {
            list.setFirst(current.getNext());
            reset();
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        SingleLinkedList list = new SingleLinkedList();
        ListIterator iterator = list.iterator();
        iterator.insertAfter(new Link(1, 1));
        iterator.insertAfter(new Link(2, 2));
        iterator.insertAfter(new Link(3, 3));
        iterator.insertBefore(new Link(4, 4));

        while (true) {
            System.out.println("Enter first letter of show ,reset,");
            System.out.println("next, get ,before ,after, delete");
            int ch = getchar();
            switch (ch) {
                case 's':
                    if (list.isEmpty()) {
                        System.out.println("链表为空");
                    } else {
                        list.displayList();
                    }
                    break;
                case 'r':
                    iterator.reset();
                    break;
                case 'n':
                    if (!list.isEmpty() && !iterator.atEnd()) {
                        iterator.next();
                    } else {
                        System.out.println("不能移动到下一个节点");
                    }
                    break;
                case 'g':
                    if (!list.isEmpty()) {

                        Link current = iterator.getCurrent();
                        System.out.println("获取到的链表值为" + current.getiData());
                    } else {
                        System.out.println("链表为空，不能获取");
                    }
                    break;
                case 'b':
                    System.out.println("请输入要插到前面的值：");
                    String param = getString();
                    iterator.insertBefore(new Link(Integer.valueOf(param), 1));
                    break;
                case 'a':
                    System.out.println("请输入要插到后面的值：");
                    String param2 = getString();
                    iterator.insertAfter(new Link(Integer.valueOf(param2), 1));
                    break;
                case 'd':
                    if (!list.isEmpty()) {
                        Link link = iterator.deleteCurrent();
                        System.out.println("删除了节点" + link.getiData());
                    } else {
                        System.out.println("链表为空，不能删除");
                    }
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        }
    }

    private static int getchar() throws IOException {
        String str = getString();
        return str.charAt(0);
    }

    private static String getString() throws IOException {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        return br.readLine();
    }
}
