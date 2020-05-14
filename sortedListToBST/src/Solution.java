import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * leetcode 109 有序链表转二叉搜索树  同样的二分法  这种链表转二叉树的问题 又有序  自然二分法，或者叫分治法是最优思路。
 * 链表的问题在于没法通过下标直接访问，所以就有了获取链表中间node的问题。而且，在递归时没法给予开始和结束位置，所以通过断开链表来解决。
 * 左子树通过在找到中间元素时断开链表解决，右子树直接就中间的next就可以了，非常简单的思路。
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

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode root = initList();
        TreeNode result = sortedListToBST(root);
        levelOrder(result);

    }

    private static ListNode initList() {
        ListNode node1 = new ListNode(-10);
        ListNode node2 = new ListNode(-3);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(9);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        return node1;
    }

    private static TreeNode sortedListToBST(ListNode root) {
        if (root == null) {
            return null;
        }
        ListNode middlePtr = getMiddle(root);
        TreeNode treeNode = new TreeNode(middlePtr.val);
        if (root == middlePtr) {
            return treeNode;
        }
        treeNode.left = sortedListToBST(root);
        treeNode.right = sortedListToBST(middlePtr.next);
        return treeNode;
    }


    //使用prev 断开链表的操作非常经典，双指针的操作未来还会多次使用
    private static ListNode getMiddle(ListNode root) {
        ListNode fastPtr = root;
        ListNode slowPtr = root;
        ListNode prevPtr = null;
        while (fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
        }
        if (prevPtr != null) {
            prevPtr.next = null;
        }
        return slowPtr;
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
}
