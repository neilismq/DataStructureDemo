package com.bj.zzq.sort;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/8
 * @Description: 通过划分快速找到中位数
 */
public class FindMedian {
    private Integer[] target;
    private int medianIndex;

    public void setTarget(Integer[] target) {
        this.target = target;
        medianIndex = target.length / 2;
    }

    public int findMedian() {
        int left = 0;
        int right = target.length - 1;
        int index;
        while ((index = partitionIt(left, right)) != medianIndex) {
            if (index > medianIndex) {
                right = index-1;
            } else {
                left = index+1;
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
        Integer[] integers = {8, 3, 5, 2, 6, 7, 1,10,11,15};
        FindMedian findMedian = new FindMedian();
        findMedian.setTarget(integers);
        int median = findMedian.findMedian();
        System.out.println("中位数为" + median);
    }

}
