package com.bj.zzq.stack;

import java.awt.*;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/11
 * @Description: 用数组实现栈
 */
public class StackWithArray {
    private long[] array;
    private int front;

    public StackWithArray(int maxSize) {
        this.array = new long[maxSize];
        front = 0;
    }

    public void push(long item) {
        if (isFull()) {
            throw new IllegalStateException("栈满");
        }
        array[front++] = item;
    }

    public long pop() {
        if (isEmpty()) {
            throw new IllegalStateException("栈空");
        }
        return array[--front];
    }

    public long peek() {
        if (isEmpty()) {
            throw new IllegalStateException("栈空");
        }
        return array[front];
    }

    public boolean isFull() {
        return front == array.length;
    }

    public boolean isEmpty() {
        return front == 0;
    }
    public static void main(String[] args){
        StackWithArray stack = new StackWithArray(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(8);
        System.out.println(stack.pop());
    }

}
