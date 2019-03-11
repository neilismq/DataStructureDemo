package com.bj.zzq.queue;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/8
 * @Description: 带计数器的队列（指针回绕，对头和队尾的指针初始指向位置不同）
 */
public class QueueWithCounter2 {
    private long[] arr;
    private int nItems;
    private int front;//队头，移除元素当前位置-1
    private int rear;//队尾，插入新元素的位置
    private int maxSize;

    public QueueWithCounter2(int maxSize) {
        this.maxSize = maxSize;
        arr = new long[maxSize];
        rear = 0;
        front = -1;
        nItems = 0;
    }

    public void insert(long item) {
        if (isFull()) {
            throw new IllegalStateException("队列已满，不能再插入数据");
        }
        arr[rear++] = item;
        if (rear == maxSize) {
            rear = 0;
        }
        nItems++;
    }

    public long pop() {
        if (isEmpty()) {
            throw new IllegalStateException("空队列，不能删除数据");
        }
        if (front == maxSize - 1) {
            front = -1;
        }
        long item = arr[++front];
        nItems--;
        return item;
    }

    public long peek() {
        if (isEmpty()) {
            throw new IllegalStateException("空队列，无数据");
        }
        if (front == maxSize - 1) {
            front = -1;
        }
        return arr[front + 1];
    }

    public boolean isFull() {
        return nItems == maxSize;
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public int size() {
        return nItems;
    }

    public static void print(long l) {
        System.out.println(l);
    }

    public static void main(String[] args) {
        QueueWithCounter2 queue = new QueueWithCounter2(5);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        print(queue.peek());
        queue.pop();
        queue.pop();
        queue.pop();
        queue.pop();
        queue.insert(10);
        print(queue.peek());
        print(queue.size());
        print(queue.pop());
        print(queue.pop());
        print(queue.size());
    }
}
