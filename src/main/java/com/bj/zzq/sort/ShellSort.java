package com.bj.zzq.sort;

import java.lang.annotation.Target;
import java.util.Random;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/2
 * @Description: 希尔排序
 */
public class ShellSort {
    private int n;//增量
    private long[] target;

    public void setTarget(long[] target, int n) {
        this.n = n;
        this.target = target;
    }

    public void sort() {
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
        display();
        for (int i = 0; i < target.length; i++) {
            long temp = target[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (temp < target[j]) {
                    target[j + 1] = target[j];
                } else {
                    break;
                }
            }
            target[j + 1] = temp;
        }
        System.out.println();
        display();
    }

    public void display() {
        for (int i = 0; i < target.length; i++) {
            System.out.print(target[i] + ",");
        }
    }

    public static void main(String[] args) {
        long[] longs = new long[10];
        Random random = new Random();
        for (int i = 0; i < longs.length; i++) {
            longs[i] = random.nextInt(10);
        }
        ShellSort shellSort = new ShellSort();
        shellSort.setTarget(longs, 4);
        shellSort.sort();
    }
}
