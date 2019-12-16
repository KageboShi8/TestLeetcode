import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import javax.xml.soap.Node;
import java.util.*;

/**
 * leetcode 144 二叉树前序遍历 没啥好说的 迭代说实话还挺耗脑子的，两种都要写熟。
 * 关于morris遍历 ，我们在中序进行探讨
 */

public class Solution {

    private static List<Integer> result = new ArrayList<>();

    public static class TreeNode {
        int val;

        public TreeNode(int val) {
            this.val = val;
        }

        TreeNode left;
        TreeNode right;
    }

    public static void main(String[] args) {
        TreeNode node = initTree();
        result = preOrderMorris(node);
        outList(result);
    }

    private static void outList(List<Integer> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    //递归 非常精简的写法
    private static List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }
        result.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);

        return result;
    }

    //迭代 使用栈结构
    private static List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            result.add(pop.val);
            if (pop.right != null) {
                stack.add(pop.right);
            }
            if (pop.left != null) {
                stack.add(pop.left);
            }
        }

        return result;
    }

    //迭代 morris遍历  不使用多余的空间
    private static List<Integer> preorderMorris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode node = root;

        while (node != null) {
            if (node.left == null) {
                result.add(root.val);
                node = node.right;
            } else {
                TreeNode predecessor = node.left;
                while (predecessor.right != null && predecessor.right != node) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    result.add(node.val);
                    predecessor.right = node;
                    node = node.left;
                } else {
                    predecessor.right = null;
                    node = node.right;
                }
            }
        }

        return result;
    }

    //morris
    private static List<Integer> preOrderMorris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode node = root;
        TreeNode prev = null;
        while (node != null) {
            if (node.left == null) {
                result.add(node.val);
                node = node.right;
            } else {
                prev = node.left;
                while (prev.right != null && prev.right != node) {
                    prev = prev.right;
                }
                if (prev.right==null){
                    result.add(node.val);
                    prev.right=node;
                    node=node.left;
                }
                else {
                    prev.right=null;
                    node=node.right;
                }

            }
        }
        return result;
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
