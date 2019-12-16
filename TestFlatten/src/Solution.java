
import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode 114 展开二叉树为链表
 * 本题的难度在于原地展开。如果只是把二叉树转链表，那很简单，直接深度优先遍历就可以，然后直接输出。顺便一提，输出使用的是层序遍历的方法，也就是广度优先遍历。
 * 问题的关键在于原地展开，就会涉及到本来的树的节点的保存问题。
 * 思路一，神仙一般的迭代的思路。因为我们要纯右树，循环步骤可以如下：左树存，右数粘贴到左树最右子叶，左树变右树，左树置空，向右迭代一位。感觉和某种排序比较像，
 * 应该是非常典型的思路。方法1——1使用了尾递归，和迭代基本没有区别
 * 思路二，直接使用递归，dfs完成。此题中脱离了尾递归。用的是在递归后整理的方法。重点是要搞清楚回溯顺序和回溯时的节点情况。说实话，写起来还是有点难的。一个dfs的
 * 回溯思路。当然，此思路对于左右子叶的处理和思路一如出一辙。而且，对于二叉树来说，每一个子叶必然都要递归，所以两次递归基本是在所难免。
 */

public class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode node = initTree();
        flatten2(node);
        outTree(node);
    }

    private static void flatten2(TreeNode node) {
        TreeNode root = node;
        flatten2_recurse(root);

    }

    //后序遍历 dfs
    private static void flatten2_recurse(TreeNode node) {
        if (node == null) {
            return;
        }
        flatten2_recurse(node.left);
        flatten2_recurse(node.right);
        TreeNode temp = node.right;
        node.right = node.left;
        node.left = null;
        while (node.right != null) {
            node = node.right;
        }
        node.right = temp;
    }


    //一个迭代的思路
    private static TreeNode flatten1(TreeNode node) {
        TreeNode root = node;
        if (root != null) {
            while (root.right != null || root.left != null) {
                if (root.left != null && root.right != null) {
                    TreeNode temp = root.left;
                    while (temp.right != null) {
                        temp = temp.right;
                    }
                    temp.right = root.right;
                    root.right = root.left;
                    root.left = null;

                } else if (root.left == null) {

                } else if (root.right == null) {
                    root.right = root.left;
                    root.left = null;
                }
                root = root.right;
            }
        }
        return node;
    }

    //尾递归解法
    private static TreeNode flatten1_1(TreeNode node) {
        TreeNode root = node;
        if (root != null) {
            flatten1_1_recurse(node);
        }
        return root;

    }

    private static void flatten1_1_recurse(TreeNode node) {
        if (node.left == null && node.right == null) {
            return;
        }
        if (node.left != null && node.right != null) {
            TreeNode temp = node.left;
            while (temp.right != null) {
                temp = temp.right;
            }
            temp.right = node.right;
            node.right = node.left;
            node.left = null;
        }
        if (node.left == null && node.right != null) {
        }
        if (node.left != null && node.right == null) {
            node.right = node.left;
            node.left = null;
        }
        flatten1_1_recurse(node.right);

    }


    //这输出不过是层序遍历 或者叫树的bfs
    private static void outTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        System.out.println(root.val);
        while (!queue.isEmpty()) {
            TreeNode thisNode = queue.poll();
            if (thisNode.left == null && thisNode.right == null) {
                continue;
            }
//            if (thisNode.left == null) {
//                System.out.println("null");
//            }
//            if (thisNode.left == null) {
//                System.out.println("null");
//            }
            if (thisNode.left != null || thisNode.right != null) {
                if (thisNode.left == null) {
                    System.out.println("null");
                } else {
                    System.out.println(thisNode.left.val);
                    queue.add(thisNode.left);
                }
                if (thisNode.right == null) {
                    System.out.println("null");
                } else {
                    System.out.println(thisNode.right.val);
                    queue.add(thisNode.right);
                }
            }

        }


    }

    private static TreeNode initTree() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node1.left = node2;
        node1.right = node5;
        node2.left = node3;
        node2.right = node4;
        node5.right = node6;


        return node1;
    }
}
