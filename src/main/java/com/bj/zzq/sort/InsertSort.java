package com.bj.zzq.sort;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/2
 * @Description: 插入排序
 */
public class InsertSort {
    private Integer[] target;

    public void setTarget(Integer[] target) {
        this.target = target;
    }

    public void sort() {
        for (int i = 0; i < target.length; i++) {
            int temp = target[i];
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
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < target.length; i++) {
            System.out.print(target[i] + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            list.add(random.nextInt(100));
        }
        insertSort.setTarget(list.toArray(new Integer[]{}));
        insertSort.display();
        long start = System.nanoTime();
        insertSort.sort();
        long end = System.nanoTime();
        insertSort.display();
        System.out.println("插入排序花费" + (end - start) + "纳秒");
        MergeSort mergeSort = new MergeSort(100);
        for (int i = 0; i < 100; i++) {
            mergeSort.insert(random.nextInt(100));
        }
        long startMerge = System.nanoTime();
        mergeSort.mergeSort();
        long endMerge= System.nanoTime();
        System.out.println("归并排序花费" + (endMerge - startMerge) + "纳秒");
        mergeSort.display();

    }
}
