package com.bj.zzq.chaintable;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/20
 * @Description: 用双端链表实现队列
 */
public class QueueWithDoubleSideLinkedList {
    private DoubleSideLinkedList list;
    private int count;

    public QueueWithDoubleSideLinkedList() {
        list = new DoubleSideLinkedList();
        count = 0;
    }

    public void insert(Link link) {
        count++;
        list.insertTail(link.getiData(), link.getdData());
    }

    public Link remove() {
        if (isEmpty()) {
            throw new IllegalStateException("队列空");
        }
        count--;
        return list.deleteFirst();
    }

    private boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return count;
    }

    public void display() {
        System.out.println();
        System.out.println("队列中数据：");
        list.displayList();
        System.out.println();
    }

    public static void main(String[] args) {
        QueueWithDoubleSideLinkedList queue = new QueueWithDoubleSideLinkedList();
        queue.insert(new Link(1, 1));
        queue.insert(new Link(2, 2));
        queue.insert(new Link(3, 3));
        queue.insert(new Link(4, 4));

        queue.display();

        Link remove = queue.remove();
        remove.displayLink();

        queue.display();
        queue.remove();

        queue.display();

    }
}
