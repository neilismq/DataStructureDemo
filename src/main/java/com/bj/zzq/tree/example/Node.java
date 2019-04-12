package com.bj.zzq.tree.example;


/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/12
 * @Description:
 */
public class Node  {
    private int count;
    private Character ch;
    private Node leftChild;
    private Node rightChild;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Character getCh() {
        return ch;
    }

    public void setCh(Character ch) {
        this.ch = ch;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

}
