package com.bj.zzq.queue;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.security.auth.login.AccountExpiredException;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/11
 * @Description: 优先级队列（数组实现）,排序为[5,4,3,2,1]
 */
public class QueueWithPriority {
    private long[] array;
    private int nItems;//队头;队尾一直数组下标0的元素

    public QueueWithPriority(int maxSize) {
        array = new long[maxSize];
        nItems = 0;
    }

    public void insert(long item) {
        if (isFull()) {
            throw new IllegalStateException("队满");
        }
        if (nItems == 0) {
            array[nItems++] = item;
            return;
        }
        int i;
        for (i = nItems - 1; i >= 0; i--) {
            if (array[i] < item) {
                array[i + 1] = array[i];
            } else {
                break;
            }
        }
        array[++i] = item;
        nItems++;
    }

    public long pop() {
        if (isEmpty()) {
            throw new IllegalStateException("队空");
        }
        return array[--nItems];
    }

    public long peek() {
        if (isEmpty()) {
            throw new IllegalStateException("队空");
        }
        return array[nItems - 1];
    }

    private boolean isFull() {
        return nItems == array.length;
    }

    private boolean isEmpty() {
        return nItems == 0;
    }


    public static void main(String[] args) {
        QueueWithPriority queue = new QueueWithPriority(5);
        queue.insert(5);
        queue.insert(8);
        queue.insert(3);
        queue.insert(7);
        queue.insert(1);
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }
}
