import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode 102 二叉树的层序遍历  迭代的非常简单，使用queue结构，注意size的取法，调整queue的大小即可。递归的话也不难，重点是要给递归加上层数这个变量以方便加入结果的list中，重点是辨明其是哪一层的。
 * 要做到5min就能写出来。
 */

public class Solution {
    private static List<List<Integer>> res = new ArrayList<>();


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
        List<List<Integer>> result = levelOrder(node);
        outList(result);
    }

    //迭代的层序
    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (queue.size() != 0) {
            List<Integer> levelList = new ArrayList();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                levelList.add(poll.val);
            }
            result.add(levelList);
        }
        return result;
    }

    //递归的层序
    private static List<List<Integer>> levelOrder_recurse(TreeNode root) {
        if (root == null) {
            return res;
        }
        levelOrderHelper(root, 0);
        return res;
    }

    private static void levelOrderHelper(TreeNode root, int level) {
        if (res.size() == level) {
            res.add(new ArrayList<Integer>());
        }
        res.get(level).add(root.val);
        if (root.left != null) {
            levelOrderHelper(root.left, level + 1);
        }
        if (root.right != null) {
            levelOrderHelper(root.right, level + 1);
        }

    }

    private static void outList(List<List<Integer>> lists) {
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                System.out.print(lists.get(i).get(j) + ",");
            }
            System.out.println(" ");
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
