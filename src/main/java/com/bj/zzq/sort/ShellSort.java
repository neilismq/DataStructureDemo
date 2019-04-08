package com.bj.zzq.sort;


import java.util.Random;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/2
 * @Description: 希尔排序
 */
public class ShellSort extends Sort {
    private Integer[] target;

    @Override
    public void setTarget(Integer[] target) {
        this.target = target;
    }

    @Override
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
                int temp = target[k];
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
//        System.out.println("h=" + n);
//        display();
//        System.out.println();
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < target.length; i++) {
            System.out.print(target[i] + " ");
        }
    }

}
