import java.util.Stack;

/**
 * leetcode 108 将有序数组转化为一颗高度平衡的二叉搜索树
 * 超典型的二分法  题中都说明了高度平衡，显然是二分法 只是要注意二分的位置 几个边界要注意调整
 */

public class Solution {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-10, -3, 0, 5, 9};
        TreeNode result = sortedArrayToBST(nums);
        levelOrder(result);
    }

    private static void levelOrder(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        while (stack.size() != 0) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = stack.pop();
                if (pop.left != null) {
                    stack.push(pop.left);
                }
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                System.out.println(pop.val);
            }
        }
    }

    private static TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        return changeHelper(nums, 0, nums.length);
    }

    private static TreeNode changeHelper(int[] nums, int start, int end) {
        if (start == end) {
            return null;
        }
        int index = (start + end) / 2;
        TreeNode node = new TreeNode(nums[index]);
        node.left = changeHelper(nums, start, index);
        node.right = changeHelper(nums, index+1, end);
        return node;
    }


}
