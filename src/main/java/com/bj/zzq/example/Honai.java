package com.bj.zzq.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/26
 * @Description: 汉诺塔问题
 */
public class Honai {

    public static Map<Integer, String> map = new HashMap<Integer, String>();

    /**
     * 递归版
     * @param n 从a柱子移动到b柱子的盘子数
     * @param a 源柱子
     * @param b 辅助柱子
     * @param c 目标柱子
     * @return
     */
    public void honai(int n, Stack<Integer> a, Stack<Integer> b, Stack<Integer> c) {
        if (n == 0) {
            return;
        }
        honai(n - 1, a, c, b);
        //打印移动顺序
        System.out.println(map.get(System.identityHashCode(a)) + " 向 " + map.get(System.identityHashCode(c)) + " 放了一个盘子" + a.peek());
        c.push(a.pop());
        honai(n - 1, b, a, c);
    }


    public static void main(String[] args) {
        Stack<Integer> a = new Stack<Integer>();
        Stack<Integer> b = new Stack<Integer>();
        Stack<Integer> c = new Stack<Integer>();
        map.put(System.identityHashCode(a), "a");
        map.put(System.identityHashCode(b), "b");
        map.put(System.identityHashCode(c), "c");

        int num = 10;
        while (num > 0) {
            a.push(num--);
        }

        System.out.println("before a中数据-->" + a.toString());
        System.out.println("before b中数据-->" + b.toString());
        System.out.println("before c中数据-->" + c.toString());

        Honai honai = new Honai();
        long start = System.nanoTime();
        honai.honai(a.size(), a, b, c);
        long end = System.nanoTime();
        System.out.println("所花时间：" + (end - start) + "纳秒");

        System.out.println("after a中数据-->" + a.toString());
        System.out.println("after b中数据-->" + b.toString());
        System.out.println("after c中数据-->" + c.toString());
    }
}
