
import java.util.*;

/**
 * leetcode 106 从中序和后续遍历构造二叉树  和前序中序一样  后续就是从后往前的根右左 所以递归顺序变一变就好了。
 * 递归参数其实在这题中不算难，难度在于index的意义 永远是中序的index减掉ileft，ileft在此处指的是中序中的左边界，所以中序的index减去左边界就是其
 * 对于中序数组的相对index，或者说，绝对长度。
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
        int[] postorder = new int[]{4, 5, 2, 6, 7, 3, 1};
        int[] inorder = new int[]{4, 2, 5, 1, 6, 3, 7};
        TreeNode root = buildTree2(inorder, postorder);
        levelOrder(root);
    }

    //逆序了后序解决了问题
    private static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null) {
            return null;
        }
        inorder = reverseArray(inorder);
        postorder = reverseArray(postorder);
        //加进map 考虑减小时间复杂度
        Map map = initMap(inorder);

        TreeNode result = buildTreeHelper(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1, map);
        return result;
    }

    //我一点也不想逆序  多说两句  关于递归的参数基本是简单的，将第一次的情况套进去基本能得个大概 问题是这个index
    private static TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null) {
            return null;
        }
        Map map = initMap(inorder);
        TreeNode root= buildTreeHelper2(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1, map);
        return root;
    }

    private static TreeNode buildTreeHelper2(int[] inorder, int[] postorder, int iLeft, int iRight, int pLeft, int pRight, Map<Integer, Integer> map) {
        if (iLeft > iRight || pLeft > pRight) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[pRight]);
        int index = map.get(postorder[pRight])-iLeft;
//        int index=0;
//        while (inorder[iLeft+index]!=postorder[pRight]){
//            index++;
//        }
        System.out.println("index="+index);
        root.right = buildTreeHelper2(inorder, postorder, iLeft + index + 1, iRight, pLeft + index, pRight - 1, map);
        root.left = buildTreeHelper2(inorder, postorder, iLeft, iLeft + index-1, pLeft, pLeft + index-1, map);
        return root;
    }

    private static int[] reverseArray(int[] postorder) {
        for (int i = 0; i < postorder.length / 2; i++) {
            int temp = postorder[i];
            postorder[i] = postorder[postorder.length - i - 1];
            postorder[postorder.length - i - 1] = temp;
        }
        return postorder;
    }

    private static TreeNode buildTreeHelper(int[] inorder, int[] postorder, int iLeft, int iRight, int pLeft, int pRight, Map<Integer, Integer> map) {
        if (iLeft > iRight || pLeft > pRight) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[pLeft]);
        Integer index = map.get(postorder[pLeft]) - iLeft;
        System.out.println("index=" + index);
        root.right = buildTreeHelper(inorder, postorder, iLeft, iLeft + index - 1, pLeft + 1, pLeft + index, map);
        root.left = buildTreeHelper(inorder, postorder, iLeft + index + 1, iRight, pLeft + index + 1, pRight, map);
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

    private static Map initMap(int[] inorder) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return map;
    }
}
