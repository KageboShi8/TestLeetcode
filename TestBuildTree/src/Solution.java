import java.util.*;

/**
 * leetcode 105 从前序和中序遍历构造二叉树  一个很简单的递归，主要是要理清流程
 * 先序的话，必然是根左右，也就带来的递归的条件。分治法的应用。归并排序还要简单一点。提请掌握。当然也有中序和后序构造二叉树的题目 106
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
        int[] inorder = new int[]{4, 2, 5, 1, 6, 3, 7};
        TreeNode root = buildTree(preorder, inorder);
        levelOrder(root);
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

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        //加进map 考虑减小时间复杂度
        Map map = initMap(inorder);
        TreeNode result = buildTreeHelper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, map);
        return result;
    }

    private static Map initMap(int[] inorder) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return map;
    }

    private static TreeNode buildTreeHelper(int[] preorder, int[] inorder, int pLeft, int pRight, int iLeft, int iRight, Map<Integer, Integer> map) {
        if (pLeft > pRight || iLeft > iRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pLeft]);
        int index = map.get(preorder[pLeft]) - iLeft;
        System.out.println("index=" + index);
        root.left = buildTreeHelper(preorder, inorder, pLeft + 1, index + pLeft, iLeft, iLeft + index - 1, map);
        root.right = buildTreeHelper(preorder, inorder, pLeft + index + 1, pRight, iLeft + index + 1, iRight, map);

        return root;
    }

}
