package com.bj.zzq.chaintable.circular;

import com.bj.zzq.chaintable.Link;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/22
 * @Description: 有N个人围成一圈，顺序排号，从第一个人开始报数，凡是报到M的人退出圈子，问最后留下的几号?假设N=7,M=4
 */
public class NumberProblem {
    public static void main(String[] args) {
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        circularLinkedList.insertNext(new Link(1, 1));
        circularLinkedList.insertNext(new Link(2, 1));
        circularLinkedList.insertNext(new Link(3, 1));
        circularLinkedList.insertNext(new Link(4, 1));
        circularLinkedList.insertNext(new Link(5, 1));
        circularLinkedList.insertNext(new Link(6, 1));
        circularLinkedList.insertNext(new Link(7, 1));
        int count = 0;
        circularLinkedList.step();
        while (circularLinkedList.getCurrent() != circularLinkedList.getCurrent().getNext()) {
            count++;
            if (count == 3) {
                Link link = circularLinkedList.deleteNext();
                System.out.println("删除：");
                link.displayLink();
                count = 0;
            }
            circularLinkedList.step();
        }
        System.out.println("最后剩下：");
        circularLinkedList.getCurrent().displayLink();
    }
}
