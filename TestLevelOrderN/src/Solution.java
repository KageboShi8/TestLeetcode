import java.lang.reflect.Array;
import java.util.*;

/**
 * leetcode 429 N叉树的层序遍历 这题也没啥，掌握了二叉树的层序，这题就是送的。在遍历children时可以用foreach循环 就这些吧
 */

public class Solution {

    private static List<List<Integer>> result=new ArrayList<>();

    public static class Node {
        private int val;
        private List<Node> children;

        public Node() {

        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node node = initTree();
        List<List<Integer>> result = levelOrder1(node);
        outList(result);
    }


    //迭代
    private static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<Node> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                if (poll.children != null) {
                    for (int j = 0; j < poll.children.size(); j++) {
                        queue.add(poll.children.get(j));
                    }
                }
                levelList.add(poll.val);
            }
            res.add(levelList);
        }
        return res;
    }

    //递归
    private static List<List<Integer>> levelOrder1(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        helper(root, 0);
        return result;
    }

    private static void helper(Node root, int level) {
        if (result.size() == level) {
            result.add(new ArrayList<Integer>());
        }
        result.get(level).add(root.val);
        if (root.children != null) {
            for (int i = 0; i < root.children.size(); i++) {
                helper(root.children.get(i), level + 1);
            }
        }
    }

    private static Node initTree() {
        Node node3 = new Node(2);
        Node node4 = new Node(4);

        Node node5 = new Node(5);
        Node node6 = new Node(6);
        List<Node> nodes56 = new ArrayList<>();
        nodes56.add(node5);
        nodes56.add(node6);

        Node node2 = new Node(3, nodes56);
        List<Node> nodes234 = new ArrayList<>();
        nodes234.add(node2);
        nodes234.add(node3);
        nodes234.add(node4);

        Node node1 = new Node(1, nodes234);
        return node1;
    }

    private static void outList(List<List<Integer>> result) {
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j) + ",");
            }
            System.out.println("___");
        }
    }

}
