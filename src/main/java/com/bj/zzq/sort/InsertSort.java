package com.bj.zzq.sort;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/2
 * @Description: 插入排序
 */
public class InsertSort extends Sort {
    private Integer[] target;

    @Override
    public void setTarget(Integer[] target) {
        this.target = target;
    }

    @Override
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

}
