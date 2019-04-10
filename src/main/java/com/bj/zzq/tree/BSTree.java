package com.bj.zzq.tree;



import java.util.Stack;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/4/8
 * @Description: 二叉搜索树,删除写完,感觉成就满满~
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

    public void inOrder() {
        reInOrder(root);
    }

    private void reInOrder(Node current) {
        if (current == null) {
            return;
        }
        reInOrder(current.getLeftChild());
        System.out.println(current.getiData());
        reInOrder(current.getRightChild());
    }

    public void postOrder() {
        rePostOrder(root);
    }

    public void rePostOrder(Node current) {
        if (current == null) {
            return;
        }
        rePostOrder(current.getLeftChild());
        rePostOrder(current.getRightChild());
        System.out.println(current.getiData());
    }

    public void preOrder() {
        rePreOrder(root);
    }

    public void rePreOrder(Node current) {
        if (current == null) {
            return;
        }
        System.out.println(current.getiData());
        rePreOrder(current.getLeftChild());
        rePreOrder(current.getRightChild());
    }

    public boolean delete(int key) {
        Node current = root;
        boolean leftSubNodeOfDeleteParent = true;
        Node parent = null;
        while (true) {
            if (current == null) {
                //没找到要删除的节点
                return false;
            }
            int iData = current.getiData();
            if (iData == key) {
                break;
            } else if (iData < key) {
                parent = current;
                current = current.getRightChild();
                leftSubNodeOfDeleteParent = false;
            } else {
                parent = current;
                current = current.getLeftChild();
                leftSubNodeOfDeleteParent = true;
            }
        }
        Node leftChild = current.getLeftChild();
        Node rightChild = current.getRightChild();
        if (leftChild == null && rightChild == null) {
            //叶子节点时
            if (parent == null) {
                //删除根节点
                root = null;
            } else {
                if (leftSubNodeOfDeleteParent) {
                    parent.setLeftChild(null);
                } else {
                    parent.setRightChild(null);
                }
            }
        } else if ((leftChild != null && rightChild == null)) {
            //只有一个左子节点
            if (parent == null) {
                root = leftChild;
            } else {
                if (leftSubNodeOfDeleteParent) {
                    parent.setLeftChild(leftChild);
                } else {
                    parent.setRightChild(leftChild);
                }
            }
            current.setLeftChild(null);
        } else if ((leftChild == null && rightChild != null)) {
            //只有一个右子节点
            if (parent == null) {
                root = rightChild;
            } else {
                if (leftSubNodeOfDeleteParent) {
                    parent.setLeftChild(rightChild);
                } else {
                    parent.setRightChild(rightChild);
                }
            }
            current.setRightChild(null);
        } else {
            //current左右两个子节点都有，找当前被删除节点的后继
            Node succeed = rightChild;
            Node succeedParent = null;
            while (succeed.getLeftChild() != null) {
                succeedParent = succeed;
                succeed = succeed.getLeftChild();
            }

            if (succeedParent != null) {
                //删除的节点的右子节点还有其他子节点
                Node succeedRightChild = succeed.getRightChild();
                if (succeedRightChild == null) {
                    //后继的右子节点为空
                    //后继的父节点和后继断开
                    succeedParent.setLeftChild(null);
                } else {
                    //后继的右子节点不为空
                    //后继的父节点和后继断开
                    succeedParent.setLeftChild(succeed.getRightChild());
                }
                //后继设置右子节点
                succeed.setRightChild(rightChild);
            }
            if (parent == null) {
                //删除根节点时

                //根节点指向后继
                root = succeed;

            } else {
                //删除的不是根节点

                if (leftSubNodeOfDeleteParent) {
                    parent.setLeftChild(succeed);
                } else {
                    parent.setRightChild(succeed);
                }
            }

            //当前被删除的节点和它右子节点断开
            current.setRightChild(null);
            //当前被删除的节点和它左子节点断开
            current.setLeftChild(null);
            //后继设置左子节点
            succeed.setLeftChild(leftChild);
        }

        return true;
    }

    public int maxDepth() {
        return reMaxDepth(root);
    }

    private int reMaxDepth(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(reMaxDepth(node.getLeftChild()), reMaxDepth(node.getRightChild())) + 1;
    }

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
                    System.out.print(pop.getiData());
                    tempStack.push(pop.getLeftChild());
                    tempStack.push(pop.getRightChild());
                    if (pop.getLeftChild() != null || pop.getRightChild() != null) {
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

    public static void main(String[] args) {
        BSTree bsTree = new BSTree();
        bsTree.insert(20, 1);
        bsTree.insert(10, 1);
        bsTree.insert(8, 1);
        bsTree.insert(14, 1);
        bsTree.insert(12, 1);
        bsTree.insert(13, 1);

        bsTree.displayTree();
        bsTree.delete(10);
        bsTree.displayTree();

    }
}
