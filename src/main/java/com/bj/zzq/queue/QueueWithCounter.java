package com.bj.zzq.queue;


/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/8
 * @Description: 带计数器的队列（指针回绕）
 */
public class QueueWithCounter {
    private long[] arr;
    private int nItems; //计数器
    private int front;//队头，移除元素（查看元素的当前位置）
    private int rear;//队尾，插入新元素的位置的前一个位置
    private int maxSize;

    public QueueWithCounter(int maxSize) {
        this.maxSize = maxSize;
        arr = new long[maxSize];
        rear = -1;
        front = 0;
        nItems = 0;
    }

    public void insert(long item) {
        if (isFull()) {
            throw new IllegalStateException("队列已满，不能再插入数据");
        }
        rear++;
        if (rear == maxSize) {
            rear = 0;
        }
        arr[rear] = item;
        nItems++;
    }

    public long pop() {
        if (isEmpty()) {
            throw new IllegalStateException("空队列，不能删除数据");
        }
        long item = arr[front++];
        if (front == maxSize) {
            front = 0;
        }
        nItems--;
        return item;
    }

    public long peek() {
        if (isEmpty()) {
            throw new IllegalStateException("空队列，无数据");
        }
        return arr[front];
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
        QueueWithCounter queue = new QueueWithCounter(5);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        //print(queue.peek());
        queue.pop();
        queue.pop();
        queue.pop();
        queue.pop();
        queue.insert(10);
        print(queue.peek());
        print(queue.size());
//        print(queue.ma);
    }
}
