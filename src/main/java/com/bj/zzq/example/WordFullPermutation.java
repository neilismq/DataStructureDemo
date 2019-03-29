package com.bj.zzq.example;

import java.util.Queue;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/29
 * @Description: 单词全排列
 */
public class WordFullPermutation {
    private char[] arr;
    private int count;

    public WordFullPermutation(String input) {
        arr = input.toCharArray();
    }

    public void fullPermutation() {
        recFullPermutation(arr.length);
    }

    public void recFullPermutation(int size) {
        if (size == 1) {
            return;
        }
        for (int i = 0; i < size; i++) {
            recFullPermutation(size - 1);
            if (size == 2) {
                showWord();
            }
            rotate(size);
        }

    }

    private void rotate(int size) {
        char temp = arr[arr.length - size];
        for (int i = size; i > 1; i--) {
            arr[arr.length - i] = arr[arr.length - i + 1];
        }
        arr[arr.length - 1] = temp;
    }

    private void showWord() {
        System.out.print(++count + " ");

        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j]);
            System.out.print("");
            System.out.flush();
        }
        System.out.println(" ");
    }

    public static void main(String[] args) {
        WordFullPermutation wordFullPermutation = new WordFullPermutation("cate");
        wordFullPermutation.fullPermutation();
    }
}
