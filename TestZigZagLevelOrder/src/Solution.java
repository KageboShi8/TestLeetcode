import java.util.*;

/**
 * leetcode 103 二叉树的锯齿层序遍历  这题没什么搞头，层序遍历，最后进入容器的顺序考虑一下就行。
 * 迭代层序遍历时，一定注意queue的大小不能是动态的，动态必然死循环
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
        TreeNode node = initTree();
        List<List<Integer>> result = zigzagLevelOrder1(node);

        outList(result);
    }

    private static void outList(List<List<Integer>> result) {
        System.out.println(result.size() + "size");
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j));
            }
            System.out.println("");
        }
    }

    //递归
    private static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result = zigzagHelper(root, 0, result);

        return result;
    }

    private static List<List<Integer>> zigzagHelper(TreeNode root, int level, List<List<Integer>> result) {
        if (root == null) {
            return result;
        }
        if (level == result.size()) {
            result.add(new ArrayList<>());
        }
        if (level % 2 == 1) {
            result.get(level).add(0, root.val);
        } else {
            result.get(level).add(root.val);
        }
        zigzagHelper(root.left, level + 1, result);
        zigzagHelper(root.right, level + 1, result);

        return result;
    }

    //迭代
    private static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            int size = queue.size();
            while (size != 0) {
                TreeNode poll = queue.poll();
                size--;
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                if (level % 2 == 1) {
                    levelList.add(0, poll.val);
                } else {
                    levelList.add(poll.val);
                }
            }
            level++;
            result.add(levelList);
        }

        return result;

    }

    private static TreeNode initTree() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);


        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        return node1;
    }
}
