package com.bj.zzq.sort;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/3
 * @Description: 快速排序
 */
public class QuickSortWithNoSmall3 extends Sort {
    private Integer[] target;

    @Override
    public void setTarget(Integer[] target) {
        this.target = target;
    }

    @Override
    public void sort() {
        recQuickSort(0, target.length - 1);
        insertSort(0, target.length - 1);
    }


    private void recQuickSort(int left, int right) {
        int size = right - left + 1;
        //此处不必拘泥于3。处理小划分的另外一种方法是采用插入排序，可以把界限设置为10，20等。Knuth推荐使用9作为切割点
        //重点：在小数组中使用插入排序被证实是最快的一种排序方法（例如小于10中）【JAVA数据结构与算法P269】
//        if (size < 10) {
//            insertSort(left, right);
//        }
        if (size > 3) {
            int pivot = middle3(left, right);
            int middle = partitionIt(left, right, pivot);
            recQuickSort(left, middle - 1);
            recQuickSort(middle + 1, right);
        }
    }

    private void insertSort(int left, int right) {
        for (int i = left + 1; i <= right; i++) {
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

    /**
     * return 右子数组的最左边界
     *
     * @param left  左边界
     * @param right 有边界
     */
    private int partitionIt(int left, int right, int pivot) {
        int leftPart = left;
        int rightPart = right - 1;
        while (true) {
            while (target[++leftPart] < pivot) ;
            //两边排序的指针要么同时到中间，要么有一方先到达
            while (target[--rightPart] > pivot) ;
            if (leftPart >= rightPart) {
                break;
            }
            swap(leftPart, rightPart);
        }
        //注意这个需要和right-1交换，因为pivot在right-1位置
        swap(leftPart, right - 1);
        return leftPart;
    }

    private void manualSort(int left, int right) {
        int size = right - left + 1;
        if (size <= 1) {
            return;
        }
        if (size == 2) {
            if (target[left] > target[right]) {
                swap(left, right);
            }
        }
        if (size == 3) {
            if (target[left] > target[right - 1]) {
                swap(left, right - 1);
            }
            if (target[left] > target[right]) {
                swap(left, right);
            }
            if (target[right - 1] > target[right]) {
                swap(right - 1, right);
            }
        }
    }


    /**
     * 当排序范围大于三个数时，找出左、中间、右中的中位数，并把这个三个数排序
     *
     * @param left
     * @param right
     * @return 中位数
     */
    private int middle3(int left, int right) {
        int middle = (left + right) / 2;
        //三个数可以考虑插入排序
        if (target[left] > target[middle]) {
            swap(left, middle);
        }
        if (target[left] > target[right]) {
            swap(left, right);
        }
        if (target[middle] > target[right]) {
            swap(middle, right);
        }
        //最左边的和最右的不用参加排序，所以把基值换到right-1位置
        swap(middle, right - 1);
        return target[right - 1];
    }

    private void swap(int left, int right) {
        int temp = target[left];
        target[left] = target[right];
        target[right] = temp;
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < target.length; i++) {
            System.out.print(target[i] + " ");
        }
    }

}
