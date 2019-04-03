package com.bj.zzq.sort;

import java.lang.annotation.Target;
import java.util.Random;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/2
 * @Description: 希尔排序
 */
public class ShellSort {
    private long[] target;

    public void setTarget(long[] target) {
        this.target = target;
    }

    public void sort() {
        int h = 1;
        while (h < target.length) {
            h = 3 * h + 1;
        }
        while (h > 1) {
            h = (h - 1) / 3;
            insertNOrder(h);
        }

    }

    private void insertNOrder(int n) {
        for (int i = 0; i < n; i++) {
            for (int k = i; k < target.length; k = k + n) {
                long temp = target[k];
                int j;
                for (j = k - n; j >= i; j = j - n) {
                    if (temp < target[j]) {
                        target[j + n] = target[j];
                    } else {
                        break;
                    }
                }
                target[j + n] = temp;
            }
        }
        System.out.println("h=" + n);
        display();
        System.out.println();
    }

    public void display() {
        for (int i = 0; i < target.length; i++) {
            System.out.print(target[i] + ",");
        }
    }

    public static void main(String[] args) {
        long[] longs = new long[100];
        Random random = new Random();
        for (int i = 0; i < longs.length; i++) {
            longs[i] = random.nextInt(100);
        }
        ShellSort shellSort = new ShellSort();
        shellSort.setTarget(longs);
        shellSort.sort();
    }
}
