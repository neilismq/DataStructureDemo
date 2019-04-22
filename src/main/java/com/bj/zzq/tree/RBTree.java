package com.bj.zzq.tree;

import com.sun.deploy.net.cookie.CookieUnavailableException;

import java.util.HashMap;
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
        newNode.isRed = true;
        if (root == null) {
            //根节点为空时，插入根节点
            root = newNode;
            root.isRed = false;
        } else {
            //根节点不为空时，向下搜寻插入点
            Node current = root;
            //有子节点,说明还未找到插入点，在下行路上
            while (current != null) {
                if (!current.isRed && current.left != null && current.right != null && current.left.isRed && current.right.isRed) {
                    exchangeColor(current);
                    //判断当前节点和父节点是否有红-红冲突，有的话解决掉它！
                    if (current.parent != null && current.parent.isRed) {
                        //解决红-红冲突
                        //todo:找结构
                        //父节点
                        Node p = current.parent;
                        //祖父节点,祖父节点肯定是黑色的。
                        Node g = p.parent;
                        //左子节点
                        Node l = current.left;
                        //右子节点
                        Node r = current.right;
                        if (isLeftNodeOfParent(p) && isLeftNodeOfParent(current)) {
                            //如果p是g的左子节点，current是p的左子节点,先变换p和g的颜色，然后以g为顶点右旋
                            reverseColor(p);
                            reverseColor(g);
                            rotateR(g);
                        } else if (!isLeftNodeOfParent(p) && !isLeftNodeOfParent(current)) {
                            //如果p是g的右子节点，current是p的右子节点，先变换p和g的颜色，然后以g为顶点左旋
                            reverseColor(p);
                            reverseColor(g);
                            rotateL(g);
                        } else if (isLeftNodeOfParent(p) && !isLeftNodeOfParent(current)) {
                            //如果p是g的左子节点，current是p的右子节点，先变换current和g的颜色，然后先以p为顶点左旋，然后以g为顶点右旋
                            reverseColor(current);
                            reverseColor(g);
                            rotateL(p);
                            rotateR(g);
                        } else {
                            //如果p是g的右子节点，current是p的左子节点，先变换current和g的颜色，然后先以p为顶点右旋，然后以g为顶点左旋
                            reverseColor(current);
                            reverseColor(g);
                            rotateR(p);
                            rotateL(g);
                        }
                    }
                }

                if (newNode.iData == current.iData) {
                    throw new IllegalArgumentException("不能插入重复值");
                } else if (newNode.iData > current.iData) {
                    if (current.right == null) {
                        break;
                    }
                    current = current.right;
                } else {
                    if (current.left == null) {
                        break;
                    }
                    current = current.left;
                }
            }
            //不管父节点是啥，先插入再说
            if (newNode.iData == current.iData) {
                throw new IllegalArgumentException("不能插入重复值");
            } else if (newNode.iData > current.iData) {
                current.right = newNode;
            } else {
                current.left = newNode;
            }
            newNode.parent = current;
            current = newNode;
            //找到了插入点
            if (current.parent.isRed) {
                //如果父节点是红色节点，产生红-红冲突
                //todo:找结构
                //父节点
                Node p = current.parent;
                //祖父节点,祖父节点肯定是黑色的。
                Node g = p.parent;
                //左子节点
                Node l = current.left;
                //右子节点
                Node r = current.right;
                if (isLeftNodeOfParent(p) && isLeftNodeOfParent(current)) {
                    //如果p是g的左子节点，current是p的左子节点,先变换p和g的颜色，然后以g为顶点右旋
                    reverseColor(p);
                    reverseColor(g);
                    rotateR(g);
                } else if (!isLeftNodeOfParent(p) && !isLeftNodeOfParent(current)) {
                    //如果p是g的右子节点，current是p的右子节点，先变换p和g的颜色，然后以g为顶点左旋
                    reverseColor(p);
                    reverseColor(g);
                    rotateL(g);
                } else if (isLeftNodeOfParent(p) && !isLeftNodeOfParent(current)) {
                    //如果p是g的左子节点，current是p的右子节点，先变换current和g的颜色，然后先以p为顶点左旋，然后以g为顶点右旋
                    reverseColor(current);
                    reverseColor(g);
                    rotateL(p);
                    rotateR(g);
                } else {
                    //如果p是g的右子节点，current是p的左子节点，先变换current和g的颜色，然后先以p为顶点右旋，然后以g为顶点左旋
                    reverseColor(current);
                    reverseColor(g);
                    rotateR(p);
                    rotateL(g);
                }
            }

        }
    }

    /**
     * 后继
     *
     * @param node 要删除的节点
     * @return
     */
    public Node findSuccessor(Node node) {
        Node current = node.right;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public boolean delete(int key) {
        // find delete node
        Node current = root;
        while (current != null) {
            if (current.iData == key) {
                break;
            } else if (current.iData < key) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        if (current == null) {
            //没找到要删除的节点
            return false;
        }

        //查找后继
        Node successor = findSuccessor(current);
        //删除节点的左子节点
        Node dl = current.left;
        //删除节点的父节点
        Node dp = current.parent;
        //后继为空时，也就是删除节点没有右子节点
        if (successor == null) {
            //如果删除的是根
            if (current == root) {
                if (dl != null) {
                    dl.isRed = false;
                    dl.parent = null;
                }
                root = dl;
            } else {
                //删除节一定是黑色，且它的左子节点一定是红色，只需要把红色节点变为黑色，然后往上替换删除节点就OK
                //如果删除节点是父节点的左子节点
                if (isLeftNodeOfParent(current)) {
                    dp.left = dl;
                } else {
                    dp.right = dl;
                }
                if (dl != null) {
                    dl.isRed = false;
                    dl.parent = dp;
                }
                current.left = null;
                current.parent = null;
            }
        } else {
            //后继不为空时
            //后继的父节点
            Node sp = successor.parent;
            //后继的左子节点
            Node sl = successor.left;
            //后继的右子节点
            Node sr = successor.right;
            //当后继的父节点和要删除的节相等时，说明后继是删除节点的右子节点，此时后继没有兄弟节点，只需要把
            if (sp == current) {

            }
            //后继节点的兄弟节点
            Node b = null;
        }


    }


    /**
     * 交换颜色。只有当当前节点是黑色，并且两个子节点都是红色时，将当前节点变为红色，两个子节点变为黑色。
     *
     * @param node
     */
    private void exchangeColor(Node node) {
        if (node != root) {
            //不是根节点才能变红色
            node.isRed = true;
        }
        node.left.isRed = false;
        node.right.isRed = false;
    }

    /**
     * 反转节点颜色
     *
     * @param node
     */
    private void reverseColor(Node node) {
        node.isRed = !node.isRed;
    }

    /**
     * 判断当前节点是否是父节点的左子节点,假设父节点不为空
     *
     * @param node 当前节点
     * @return
     */
    private boolean isLeftNodeOfParent(Node node) {
        Node p = node.parent;
        return node == p.left;
    }

    /**
     * 左旋,至少有一个右子节点
     *
     * @param node 顶点
     */
    private void rotateL(Node node) {
        //父节点
        Node p = node.parent;
        //右子节点
        Node r = node.right;
        //左子节点
        Node l = node.left;
        //右子节点的左子节点
        Node rl = r.left;
        if (p != null) {
            if (isLeftNodeOfParent(node)) {
                p.left = r;
            } else {
                p.right = r;
            }
            r.parent = p;
        }

        r.left = node;
        node.parent = r;
        if (rl != null) {
            rl.parent = node;
        }
        node.right = rl;
        //如果以根为顶点
        if (node == root) {
            root = r;
            root.isRed = false;
        }
    }

    /**
     * 右旋，至少有一个左子节点
     *
     * @param node 顶点
     */
    private void rotateR(Node node) {
        //父节点
        Node p = node.parent;
        //左子节点
        Node l = node.left;
        //左子节点的右子节点
        Node lr = l.right;
        if (p != null) {
            if (isLeftNodeOfParent(node)) {
                p.left = l;
            } else {
                p.right = l;
            }
            l.parent = p;
        }
        l.right = node;
        node.parent = l;
        if (lr != null) {
            lr.parent = node;
        }
        //这个地方要注意，没有lr时也要置为空
        node.left = lr;
        //如果以根为顶点
        if (node == root) {
            root = l;
            root.isRed = false;
        }
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
        System.out.println("***************** start display tree ******************");
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
        System.out.println("**************** end display tree ****************");
    }

    static final class Node {
        boolean isRed;
        int iData;
        Node left;
        Node right;
        Node parent;

        Node(int iData) {
            this.iData = iData;
        }
    }

    public static void main(String[] args) {
        RBTree tree = new RBTree();
        tree.insert(new Node(50));
        tree.displayTree();
        tree.insert(new Node(25));
        tree.displayTree();
        tree.insert(new Node(75));
        tree.displayTree();
        tree.insert(new Node(12));
        tree.displayTree();
        tree.insert(new Node(18));
        tree.displayTree();
        tree.insert(new Node(15));
        tree.displayTree();
        tree.insert(new Node(11));
        tree.displayTree();
        tree.insert(new Node(10));
        tree.displayTree();


    }
}
