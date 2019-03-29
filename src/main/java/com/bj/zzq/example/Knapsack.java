package com.bj.zzq.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/28
 * @Description: 背包问题：试图将不同重量的数据项放入背包中，使得背包最后达到指定的总重量。不需要将所有的选项都放入背包中。
 * 比如想要背包最后承重20kg,并且有5个可以放入的数据项，分别是11kg、9kg、8kg、7kg、5kg
 */
public class Knapsack {
    private Stack<Integer> stack;

    public Knapsack() {
        stack = new Stack<Integer>();
    }

    public boolean knapsack(ArrayList<Integer> list, int sum) {

        Integer data = list.get(0);
        ArrayList<Integer> list2 = new ArrayList<Integer>(list);
        if (list.contains(sum)) {

            //todo:找到了
            System.out.println("暂时发现:" + data);
            return true;
        } else{

        }
        if (data < sum) {
            //todo:还需要继续找,包含当前
            list2.remove(data);
            boolean result = knapsack(list2, sum - data);
            if (result) {
                System.out.println("暂时发现：" + data);
            }
            return result;
        } else {
            //todo:还需要继续找，不包含当前
            boolean result = knapsack(list2, sum);
            if (result) {
                System.out.println("暂时发现：" + data);
            }
            return result;
        }

    }

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        knapsack.knapsack(list, 10);
    }
}
