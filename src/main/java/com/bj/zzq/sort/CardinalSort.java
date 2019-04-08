package com.bj.zzq.sort;

import java.util.LinkedList;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/8
 * @Description: 基数排序
 */
public class CardinalSort {
    private int cardinalNum;//基数
    private Integer[] target;//原数组
    private LinkedList<Integer>[] tempList;

    public void setCardinalNum(int cardinalNum) {
        this.cardinalNum = cardinalNum;
        tempList = new LinkedList[cardinalNum];
        for (int i = 0; i < tempList.length; i++) {
            tempList[i] = new LinkedList<Integer>();
        }
    }

    public void setTarget(Integer[] target) {
        this.target = target;
    }

    public void sort() {
        //说明全部数据都在index=0的链表中时退出
        int digit = 1;//从个位往前推
        while (true) {
            for (int i = 0; i < target.length; i++) {
                int extractNum = extractDigitNum(target[i], digit);
                tempList[extractNum].add(target[i]);
            }
            if (tempList[0].size() == target.length) {
                copyToOrigin();
                break;
            }
            copyToOrigin();
            digit++;
        }

    }

    private void copyToOrigin() {
        int index = 0;
        for (int i = 0; i < tempList.length; i++) {
            LinkedList<Integer> list = tempList[i];
            while (!list.isEmpty()) {
                target[index++] = list.remove();
            }
        }
    }

    private int extractDigitNum(Integer target, int digit) {
        String str = String.valueOf(target);
        if (str.length() - digit < 0) {
            return 0;
        }
        return Character.getNumericValue(str.charAt(str.length() - digit));
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < target.length; i++) {
            System.out.print(target[i] + " ");
        }
    }

    public static void main(String[] args) {
        CardinalSort cardinalSort = new CardinalSort();
        cardinalSort.setCardinalNum(10);
        Integer[] array = {1232, 1321, 32, 43, 432, 5, 43, 543, 6546, 54, 2, 323, 21, 543, 543};
        cardinalSort.setTarget(array);
        cardinalSort.display();
        cardinalSort.sort();
        cardinalSort.display();
    }
}
