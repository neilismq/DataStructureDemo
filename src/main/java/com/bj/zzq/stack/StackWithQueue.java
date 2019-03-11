package com.bj.zzq.stack;

import com.bj.zzq.queue.QueueWithCounter;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/11
 * @Description: 用队列实现栈
 */
public class StackWithQueue {
    private QueueWithCounter queue1;//原队列
    private QueueWithCounter queue2;//辅助队列
    private int maxSize;

    public StackWithQueue(int num) {
        this.maxSize = num;
        queue1 = new QueueWithCounter(num);
        queue2 = new QueueWithCounter(num);
    }

    public void insert(long item) {
        if (isFull()) {
            throw new IllegalStateException("栈满，不能插入数据");
        }
        queue1.insert(item);
    }

    public long pop() {
        if (isEmpty()) {
            throw new IllegalStateException("栈为空，不能弹出数据");
        }
        long popData = 0;
        while (!queue1.isEmpty()) {
            long pop = queue1.pop();
            if (!queue1.isEmpty()) {
                queue2.insert(pop);
                continue;
            }
            popData = pop;
        }
        queue1 = queue2;
        queue2 = new QueueWithCounter(maxSize);
        return popData;
    }

    public long peek() {
        if (isEmpty()) {
            throw new IllegalStateException("栈为空，不能查看数据");
        }
        long popData = 0;
        while (!queue1.isEmpty()) {
            long pop = queue1.pop();
            queue2.insert(pop);
            if (!queue1.isEmpty()) {
                continue;
            }
            popData = pop;
        }
        queue1 = queue2;
        queue2 = new QueueWithCounter(maxSize);
        return popData;
    }

    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    public boolean isFull() {
        return queue1.isFull();
    }

    public int size() {
        return queue1.size();
    }

}
