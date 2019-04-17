package com.bj.zzq.tree;

import java.util.Stack;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/15
 * @Description: 红黑树实现
 * <p>
 * 红黑-规则:
 * 1、每一个节点不是红色就是黑色的
 * 2、根总是黑色的
 * 3、如果节点是红色的，则它的子节点必须是黑色的。（反之不一定为真）
 * 4、从根到叶节点和空子节点的每条路径，必须包含相同数目的黑色节点。
 * <p>
 * 空子节点概念：
 * 当一个节点只有左子节点，其不存在的右子节点就是空子节点。同理，当一个节点只有右子节点，其不存在的左子节点也是空子节点。
 * <p>
 * 红黑树为什么是平衡树：
 * 不严谨推断:试着创建一棵树，假设它已经超过两层不平衡了,但是要满足红黑规则。事实证明，这是不可能的。如果一条路径上的节点数比另一条路径上的节点数
 * 多一个以上（至少两个）,那它要么有更多的黑色节点（违背了规则4）,要么有两个相邻的红色节点（违背了规则3）。
 * <p>
 * 插入时一些有用的规则：
 * 1、新插入的节点总是红色的。插入一个红色节点比插入黑色节点违背红黑-规则的机会小。这是因为如果把新插入的红色节点连接到黑色节点上，不会违背规则。
 * 它不会造成两个红色节点在一起的情况（规则3），而且也不会改变任何路径上的黑色高度（规则4）。如果把新的红色节点连接到红色节点上，还是会违背规则3。
 * 所以，不管怎样，插入红色节点时只会有一半的机会违背规则3。但是如果插入的节点是黑色的，就总会违反规则4。还有，违背规则3比违背规则4更容易修正。
 * 2、右旋：选中一个节点作为旋转的顶端（top）,这个顶端节点会向下和向右移动到它右子节点的位置，它的左子节点将会上移到它原来的位置。设原来顶端为t,
 * t的左子节点为p,p的右子节点为s,右旋转t后，s最终会连接到t的左节点。注意：如果做右旋，顶端节点必须有一个左子节点。
 * 3、左旋：选中一个节点作为旋转的顶端（top）,这个顶端节点会向下和向左移动到它左子节点的位置，它的右子节点将会上移到它原来的位置。设原来顶端为t,
 * t的右子节点为p,p的左子节点为s,左旋转t后，s最终会连接到t的右节点。注意：如果做左旋，顶端节点必须有一个右子节点。
 * <p>
 * 插入时逻辑：
 * 1、在向下寻找插入点的时候，检查当前当前节点是否是黑色，以及它的两个子节点是否是红色。如果是这样，改变这个三个数据项的颜色。
 * （黑变红，红变黑，但根节点除外，因为根节点永远是黑色）
 * 2、在颜色变换后，检查有没有违背规则3，如果有，执行适当的旋转：对外侧子孙节点执行一次旋转，对内侧子孙节点执行两次旋转（第一次将内子孙段节点旋转到外侧）。
 * 3、当到达一个叶节点时，插入红色节点。再次检查是否违背规则3，然后执行对应的旋转操作。
 */
public class RBTree {
    private Node root;

    public int maxDepth() {
        return reMaxDepth(root);
    }

    private int reMaxDepth(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(reMaxDepth(node.left), reMaxDepth(node.right)) + 1;
    }

    public void insert(Node newNode) {

    }

    /**
     * 显示红黑树，后缀是R代表红色节点，后缀是B代表黑色节点
     */
    public void displayTree() {
        Stack<Node> globalStack = new Stack<Node>();
        globalStack.push(root);
        Double pow = Math.pow(2, maxDepth());
        int blankNums = pow.intValue();
        boolean rowIsEmpty = false;
        System.out.println("*****************start display tree******************");
        while (!rowIsEmpty) {
            for (int i = 0; i < blankNums; i++) {
                System.out.print(" ");
            }
            Stack<Node> tempStack = new Stack<Node>();
            rowIsEmpty = true;
            while (!globalStack.isEmpty()) {
                Node pop = globalStack.pop();
                if (pop == null) {
                    System.out.print("--");
                    tempStack.push(null);
                    tempStack.push(null);
                } else {
                    System.out.print(pop.iData + (pop.isRed ? "R" : "B"));
                    tempStack.push(pop.left);
                    tempStack.push(pop.right);
                    if (pop.left != null || pop.right != null) {
                        rowIsEmpty = false;
                    }
                }
                for (int i = 0; i < 2 * blankNums - 2; i++) {
                    System.out.print(" ");
                }
            }
            blankNums = blankNums / 2;
            System.out.println();
            while (!tempStack.isEmpty()) {
                globalStack.push(tempStack.pop());
            }
        }
        System.out.println("**********************display tree end****************");
    }

    static final class Node {
        boolean isRed;
        int iData;
        Node left;
        Node right;
        Node parent;
    }

    public static void main(String[] args) {

    }
}
