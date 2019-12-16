import java.util.*;

/**
 * leetcode 107 二叉树的层序遍历2 从底向上的层序遍历；
 * 这题在技法方面基本没什么，无非还是那些老一套的bfs，多的只是数学技巧而已
 */


public class Solution {
    static List<List<Integer>> result = new ArrayList<>();

    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode node = initNode();
        result = levelOrderBottom1(node);
        outList(result);
    }

    //递归再翻转，自然是简单。
    private static List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return result;
        }
        levelOrderBottomHelper(root, 0);
        for (int i = 0; i < result.size() / 2; i++) {
            List<Integer> list = result.get(i);
            result.set(i, result.get(result.size() - 1 - i));
            result.set(result.size() - 1 - i, list);
        }
        return result;
    }

    private static void levelOrderBottomHelper(TreeNode root, int level) {
        if (result.size() == level) {
            result.add(new ArrayList<Integer>());
        }
        result.get(level).add(root.val);
        if (root.left != null) {
            levelOrderBottomHelper(root.left, level + 1);
        }
        if (root.right != null) {
            levelOrderBottomHelper(root.right, level + 1);
        }
    }

    //迭代直接搞定 思路上也没有什么变化，同样是得到一切再取反
    private static List<List<Integer>> levelOrderBottom1(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                list.add(poll.val);
            }
            res.add(list);
        }

        for (int i = 0; i < res.size() / 2; i++) {
            List<Integer> list = res.get(i);
            res.set(i, res.get(res.size() - 1 - i));
            res.set(res.size() - 1 - i, list);
        }
        return res;
    }

    //递归 非常精简的写法
    private static List<List<Integer>> levelOrderBottom2(TreeNode root) {
        helper(root, 0);
        return result;
    }

    private static void helper(TreeNode root, int level) {
        if (root == null) return;
        if (level == result.size()) {
            result.add(0, new ArrayList<>());
        }
        result.get(result.size() - level - 1).add(root.val);
        helper(root.left, level + 1);
        helper(root.right, level + 1);
    }


    private static void outList(List<List<Integer>> result) {
        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++) {
            System.out.println("第" + i + "个list=");
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j) + ",");
            }
        }
    }

    private static TreeNode initNode() {
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
