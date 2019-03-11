package com.bj.zzq.queue;

import com.bj.zzq.stack.StackWithQueue;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/11
 * @Description: 用栈实现队列
 */
public class QueueWithStack {
    private StackWithQueue stack1;//数据栈
    private StackWithQueue stack2;//辅助栈

    public QueueWithStack(int maxSize) {
        stack1 = new StackWithQueue(maxSize);
        stack2 = new StackWithQueue(maxSize);
    }

    public void insert(long item) {
        if (isFull()) {
            throw new IllegalStateException("队列满，不能插入数据");
        }
        stack1.insert(item);
    }

    public long pop() {
        if (isEmpty()) {
            throw new IllegalStateException("队列为空，不能弹出数据");
        }
        while (!stack1.isEmpty()) {
            long pop = stack1.pop();
            stack2.insert(pop);
        }
        long popData = stack2.pop();
        while (!stack2.isEmpty()) {
            stack1.insert(stack2.pop());
        }
        return popData;
    }

    public long peek() {
        if (isEmpty()) {
            throw new IllegalStateException("队列为空，不能查看数据");
        }
        while (!stack1.isEmpty()) {
            long pop = stack1.pop();
            stack2.insert(pop);
        }
        long peekData = stack2.peek();
        while (!stack2.isEmpty()) {
            stack1.insert(stack2.pop());
        }
        return peekData;
    }

    public boolean isFull() {
        return stack1.isFull();
    }

    public boolean isEmpty() {
        return stack1.isEmpty();
    }

    public int size() {
        return stack1.size();
    }

    public static void main(String[] args) {
        QueueWithStack queue = new QueueWithStack(6);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        queue.insert(6);
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.size());
        queue.insert(8);

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());

    }
}
