package com.bj.zzq.sort;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/3
 * @Description: 快速排序
 */
public class QuickSort {
    private int[] target;

    public QuickSort(int[] target) {
        this.target = target;
    }

    public void sort() {
        recQuickSort(0, target.length - 1);
    }

    public void recQuickSort(int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = partitionIt(left, right);
        recQuickSort(left, middle - 1);
        recQuickSort(middle + 1, right);
    }

    /**
     * return 右子数组的最左边
     *
     * @param left  左边界
     * @param right 有边界
     */
    private int partitionIt(int left, int right) {
        int pivot = target[right];
        int tempRight = right;
        left = left - 1;
        right = right + 1;
        while (true) {
            while (left <= right && target[++left] < pivot) ;
            while (right >= left && target[--right] > pivot) ;
            if (left >= right) {
                break;
            }
            swap(left, right);
        }
        swap(left, tempRight);
        display();
        return left;
    }

    private void swap(int left, int right) {
        int temp = target[left];
        target[left] = target[right];
        target[right] = temp;
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < target.length; i++) {
            System.out.print(target[i] + ",");
        }
    }

    public static void main(String[] args) {
        int[] intA = {2, 4, 3, 6, 1, 7, 4, 9, 6};
        QuickSort quickSort = new QuickSort(intA);
        quickSort.display();
        quickSort.sort();
    }
}
