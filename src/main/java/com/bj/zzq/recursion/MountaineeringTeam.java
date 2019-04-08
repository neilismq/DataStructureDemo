package com.bj.zzq.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/2
 * @Description: 组合C5-3
 */
public class MountaineeringTeam {
    private Stack<Character> stack;
    private ArrayList<Character> list;
    private int n;
    private int nItem;

    public MountaineeringTeam(ArrayList<Character> list, int n) {
        this.list = list;
        this.n = n;
        stack = new Stack<Character>();
    }

    public void find() {
        combine(list, n);
    }

    /**
     * @param teams 队伍
     * @param n     需要找到的人数
     */
    public void combine(List<Character> teams, int n) {
        if (teams.size() == 0 || n <= 0) {
            displayStack();
        } else {
            ArrayList<Character> listTemp1 = new ArrayList<Character>(teams);
            ArrayList<Character> listTemp2 = new ArrayList<Character>(teams);
            Character remove = listTemp1.remove(0);
            stack.push(remove);
            combine(listTemp1, n - 1);
            stack.pop();
            listTemp2.remove(0);
            combine(listTemp2, n);
        }
    }

    private void displayStack() {
        if (stack.size() == n) {
            nItem++;
            System.out.println(stack.toString());
        }
    }

    public int size() {
        return this.nItem;
    }

    public static void main(String[] args) {
        ArrayList<Character> list = new ArrayList<Character>();

        list.add('A');
        list.add('B');
        list.add('C');
        list.add('D');
        list.add('E');


        MountaineeringTeam team = new MountaineeringTeam(list, 3);
        team.find();

        System.out.println(team.size());
    }
}
