import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

/**
 * leetcode104  二叉树的最大深度  dfs的一点准备  递归瞬秒 当然 bfs也可以，可是就显得臃肿的多了。
 */

public class Solution {
    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode node = initTree();
        int depth = maxDepth(node);
        System.out.println(depth);
    }

    //递归 这个递归不难写  注意写法技巧  我写的肯定没这么精妙
    private static int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int left = maxDepth(node.left);
            int right = maxDepth(node.right);
            return Math.max(left, right) + 1;
        }
    }


    private static TreeNode initTree() {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        return node1;
    }

}
