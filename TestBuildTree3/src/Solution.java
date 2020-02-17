import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode 889 从前序和后序遍历构造二叉树
 */

public class Solution {

    public static class TreeNode {
        int val;

        public TreeNode(int val) {
            this.val = val;
        }

        TreeNode left;
        TreeNode right;
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{1, 2, 4, 5, 3, 6, 7};
        int[] postorder = new int[]{4, 5, 2, 6, 7, 3, 1};
        TreeNode root = buildTree3(preorder, postorder);
        levelOrder(root);
    }

    static int preIndex = 0;
    static int postIndex = 0;

    //还得研究
    private static TreeNode buildTree3(int[] pre, int[] post) {
        TreeNode root=new TreeNode(pre[preIndex++]);
        if (root.val!=post[postIndex]){
            root.left=buildTree3(pre,post);
        }
        if (root.val!=post[postIndex]){
            root.right=buildTree3(pre,post);
        }
        postIndex++;
        return root;
    }


    private static void levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (queue.size() != 0) {
            TreeNode poll = queue.poll();
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
            System.out.println(poll.val);
        }
    }
}
