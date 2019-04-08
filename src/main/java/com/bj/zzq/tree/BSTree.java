package com.bj.zzq.tree;


/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/8
 * @Description: 二叉搜索树
 */
public class BSTree {
    private Node root;

    public Node find(int key) {
        Node current = root;
        while (true) {
            if (current == null) {
                return null;
            }
            int iData = current.getiData();
            if (iData == key) {
                return current;
            } else if (iData < key) {
                current = current.getRightChild();
            } else {
                current = current.getLeftChild();
            }
        }
    }

    public void insert(int iData, double dData) {
        Node node = new Node();
        node.setiData(iData);
        node.setdData(dData);
        if (root == null) {
            root = node;
            return;
        }
        Node current = root;
        while (true) {
            int tempiData = current.getiData();
            Node rightChild = current.getRightChild();
            Node leftChild = current.getLeftChild();
            if (tempiData <= iData) {
                if (rightChild == null) {
                    current.setRightChild(node);
                    break;
                } else {
                    current = rightChild;
                }
            } else if (tempiData > iData) {
                if (leftChild == null) {
                    current.setLeftChild(node);
                    break;
                } else {
                    current = leftChild;
                }
            }
        }
    }

    public void delete(int key) {

    }

    public static void main(String[] args) {
        BSTree bsTree = new BSTree();
        bsTree.insert(1, 3);
        bsTree.insert(5, 1);
        bsTree.insert(2, 11);
        bsTree.insert(7, 111);
        bsTree.insert(8, 1111);
        bsTree.insert(9, 11111);
        bsTree.insert(10, 111111);

        Node node = bsTree.find(9);
        node.displayNode();
    }
}
