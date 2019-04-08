package com.bj.zzq.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/28
 * @Description: 背包问题：试图将不同重量的数据项放入背包中，使得背包最后达到指定的总重量。不需要将所有的选项都放入背包中。
 * 比如想要背包最后承重20kg,并且有5个可以放入的数据项，分别是11kg、9kg、8kg、7kg、5kg。找出所有的可能性。(此程序中数据项可以重复)
 */
public class Knapsack {
    private Stack<Integer> target;//目标组合栈
    private List<Integer> originList;//源数据链表


    public Knapsack(List originList) {
        target = new Stack<Integer>();
        this.originList = originList;
    }

    /**
     * 在list中找到和为sum的所有组合
     *
     * @param list 源数据链表
     * @param sum  和
     * @return
     */
    public void knapsack(ArrayList<Integer> list, int sum) {
        /**
         * 如果到最后一个数还没有找到指定sum
         */
        if (list.size() == 0 && sum > 0) {
            return;
        }
        //防止影响源数据
        ArrayList<Integer> list2 = new ArrayList<Integer>(list);
        //取第一个数据
        Integer data = list2.get(0);
        //debug用
        System.out.println("列表中数据：" + list.toString() + " 寻找 " + sum + "," + "栈中数据：" + target.toString());
        //如果找到了sum
        if (data == sum) {
            //入组合栈，然后显示数据
            target.push(data);
            showData();
            //栈弹出这个数据，数据链表排除这个数据后继续查找sum
            target.pop();
            list2.remove(data);
            knapsack(list2, sum);
        } else if (data > sum) {
            //先移除这个数据
            list2.remove(data);
            //返回重新寻找的结果
            knapsack(list2, sum);
        } else {
            target.push(data);
            list2.remove(data);
            knapsack(list2, sum - data);
            target.pop();
            knapsack(list2, sum);
        }
    }

    private void showData() {
        System.out.println(">>>>>>>>>>>>>>>>>找到一组数据：" + target.toString());
    }

    public static void main(String[] args) {
        //找到1-9中所有加起来等于10的组合
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        Knapsack knapsack = new Knapsack(list);
        knapsack.knapsack(list, 10);
    }
}
