package com.bj.zzq.example;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/28
 * @Description: 乘方问题
 */
public class Power {
    public static void main(String[] args) {
        Power power = new Power();
        System.out.println(power.power(2,6));
        System.out.println(power.power(2,5));
        System.out.println(power.power(1,10));
    }
    

    /**
     * @param base     底数
     * @param exponent 指数
     */
    public int power(int base, int exponent) {
        if (exponent == 1) {
            return base;
        }
        if (exponent % 2 == 0) {
            return power(base * base, exponent / 2);
        } else {
            return power(base * base, exponent / 2) * base;
        }
    }
}
