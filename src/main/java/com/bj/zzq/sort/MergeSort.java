package com.bj.zzq.sort;

import java.util.Random;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/27
 * @Description: 归并排序
 */
public class MergeSort {
    private Integer[] workSpace;//中间数组
    private Integer[] origin;//原数组
    private int nums;

    public void setTarget(Integer[] origin) {
        this.origin = origin;
        this.nums = origin.length;
    }

    public void sort() {
        workSpace = new Integer[nums];
        merge(workSpace, 0, nums - 1);
    }


    public void merge(Integer[] workSpace, int start, int end) {
        if (end == start) {
            return;
        }
        int mid = (start + end) / 2;
        merge(workSpace, start, mid);
        merge(workSpace, mid + 1, end);
        recMerge(workSpace, start, mid, end);
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < nums; i++) {
            System.out.print(origin[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    //合并两个有序数组
    private void recMerge(Integer[] workSpace, int start, int mid, int end) {
        // start - mid
        //mid+1 - end
        int j = 0;
        int beforeIndex = start;
        int afterIndex = mid + 1;
        while (beforeIndex <= mid && afterIndex <= end) {
            if (origin[beforeIndex] < origin[afterIndex]) {
                workSpace[start + j++] = origin[beforeIndex++];
            } else {
                workSpace[start + j++] = origin[afterIndex++];
            }
        }
        while (beforeIndex <= mid) {
            workSpace[start + j++] = origin[beforeIndex++];
        }

        while (afterIndex <= end) {
            workSpace[start + j++] = origin[afterIndex++];
        }

        while (j > 0) {
            --j;
            origin[start + j] = workSpace[start + j];
        }
    }
}
