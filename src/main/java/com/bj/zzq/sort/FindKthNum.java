package com.bj.zzq.sort;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/8
 * @Description: 选择的意思是从一个数组中找出第k大或者第k小的数据项，找中心数只是一个特列
 */
public class FindKthNum {

    private Integer[] target;
    private int kIndex;//第k大

    public void setTarget(Integer[] target) {
        this.target = target;
    }

    public void setK(int k) {
        kIndex = target.length - k;
    }

    public int findMedian() {
        int left = 0;
        int right = target.length - 1;
        int index;
        while ((index = partitionIt(left, right)) != kIndex) {
            if (index > kIndex) {
                right = index - 1;
            } else {
                left = index + 1;
            }
        }
        return target[index];
    }

    private int partitionIt(int left, int right) {
        int pivot = target[right];
        int leftPart = left - 1;
        int rightPart = right;
        while (true) {
            while (leftPart < rightPart && target[++leftPart] < pivot) ;
            while (leftPart < rightPart && target[--rightPart] > pivot) ;
            if (leftPart >= rightPart) {
                break;
            }
            swap(leftPart, rightPart);
        }
        swap(leftPart, right);
        return leftPart;
    }

    private void swap(int leftPart, int rightPart) {
        int temp = target[leftPart];
        target[leftPart] = target[rightPart];
        target[rightPart] = temp;
    }

    public static void main(String[] args) {
        Integer[] integers = {8, 3, 5, 2, 6, 7, 1, 10, 11, 15};
        FindKthNum findMedian = new FindKthNum();
        findMedian.setTarget(integers);
        findMedian.setK(3);
        int median = findMedian.findMedian();
        System.out.println("k=3时为" + median);
    }

}
