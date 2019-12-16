import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * leetcode 94 二叉树的中序遍历 关于morris遍历倒是看懂了。主要是对于后置节点的应用。在后置节点为null时，调整后置节点位置为遍历的下一位，然后再操作拆除它。非常精妙的省空间的方法。
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
        List<Integer> result = inorderTraversalMorris(node);
        outList(result);
    }

    //递归
    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return list;
        }
        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);
        return list;
    }

    //迭代 迭代的话要注意栈的使用  和先序遍历不同，这里的栈要先把left全入栈，再进行后面的操作
    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }


        return result;
    }

    //morris遍历 线索二叉树
    public static List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> result = new ArrayList();
        if (root == null) return result;
        TreeNode node = root;
        TreeNode prev = null;
        while (node != null) {
            if (node.left == null) {
                result.add(node.val);
                node = node.right;
            } else {
                prev=node.left;
                while (prev.right!=null&&prev.right!=node){
                    prev=prev.right;
                }
                if (prev.right==null){
                    prev.right=node;
                    node=node.left;
                }else {
                    result.add(node.val);
                    prev.right=null;
                    node=node.right;
                }
            }
        }

        return result;
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
