package com.bj.zzq.sort;

import java.util.Random;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/27
 * @Description: 归并排序
 */
public class MergeSort {
    private long[] workSpace;//中间数组
    private long[] origin;//原数组
    private int nums;

    public MergeSort(int max) {
        origin = new long[max];
    }

    public void insert(long item) {
        origin[nums++] = item;
    }

    public void mergeSort() {
        workSpace = new long[nums];
        merge(workSpace, 0, nums - 1);
    }


    public void merge(long[] workSpace, int start, int end) {
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
    private void recMerge(long[] workSpace, int start, int mid, int end) {
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

    public static void main(String[] args) {
        MergeSort sort = new MergeSort(100);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            sort.insert(random.nextInt(100));
        }
        sort.display();
        sort.mergeSort();
        sort.display();


    }

}
