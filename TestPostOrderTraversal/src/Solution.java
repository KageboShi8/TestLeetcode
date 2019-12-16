import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import java.util.*;

/**
 * leetcode 145 二叉树的后序遍历
 */


public class Solution {
    static List<Integer> list = new ArrayList<>();


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode node = initTree();
        List<Integer> result = postorderTraversalMorris(node);
        outList(result);
    }

    //递归
    private static List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return list;
        }
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        list.add(root.val);

        return list;
    }

    //迭代 用的完全是前序遍历再反过来插入的方法，投机取消，无可厚非
    private static List<Integer> postorderTraversal1(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            result.addFirst(pop.val);
            if (pop.left != null) {
                stack.add(pop.left);
            }
            if (pop.right != null) {
                stack.add(pop.right);
            }
        }
        return result;
    }

    //morris遍历 线索二叉树 有点旋转  关于逆序输出的那块有点晕 关于为什么需要dummy 也有点晕
    private static List<Integer> postorderTraversalMorris(TreeNode root) {
        if (root == null) return list;
        TreeNode dummy=new TreeNode(0);
        dummy.left=root;
        TreeNode node = dummy;
        TreeNode prev = null;
        while (node != null) {
            if (node.left == null) {
                node = node.right;
            } else {
                prev = node.left;
                while (prev.right != null && prev.right != node) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = node;
                    node = node.left;
                } else {
                    outReverse(node.left, prev);
                    prev.right = null;
                    node = node.right;
                }
            }
        }


        return list;
    }

    private static void outReverse(TreeNode node1, TreeNode node2) {
        reverse(node1, node2);
        TreeNode node=node2;
        while (node!=node1){
            list.add(node.val);
            node=node.right;
        }
        list.add(node1.val);
        reverse(node2,node1);
    }

    private static void reverse(TreeNode node1, TreeNode node2) {
        TreeNode prev = node1;
        TreeNode current = prev.right;
        TreeNode next = current.right;
        while (prev != node2) {
            current.right = prev;
            prev = current;
            current = next;
            next = next.right;
        }
    }

    private static void outList(List<Integer> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    private static TreeNode initTree() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left = node2;
        node2.left = node4;
        node2.right = node5;
        node1.right = node3;

        return node1;
    }
}
