/**
 * leetcode 24 交换链表相邻节点
 * 很简单的题目，迭代法是一定要做出来的。重点在于写法，递归必须要练；
 * 迭代的话要搞清楚 赋值的顺序，以免节点被覆盖，递归的话，要明白整个的流程，那些是递归前的哪些是递归后的流程
 */

public class Solution {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode l = initListNode();
//        ListNode result = swapPairs2(l);
        ListNode result = swap3Pairs2(l);
        outLists(result);
    }

    //三数字翻转 递归
    private static ListNode swap3Pairs(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode second = head.next;
        ListNode third = head.next.next;
        head.next = swap3Pairs(third.next);
        third.next = second;
        second.next = head;
        return third;
    }

    //三数字翻转 迭代
    private static ListNode swap3Pairs2(ListNode head) {
        ListNode dummy1 = new ListNode(-1);
        dummy1.next = head;
        ListNode temp = dummy1;
        while (temp.next != null && temp.next.next != null && temp.next.next.next != null) {
            ListNode first = temp.next;
            ListNode second = temp.next.next;
            ListNode third = temp.next.next.next;
            temp.next = third;
            first.next = third.next;
            third.next = second;
            second.next = first;
            temp = first;
        }
        return dummy1.next;
    }

    //非常浅显的递归解法  我还是不会
    private static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs2(next.next);
        next.next = head;
        return next;
    }


    //迭代解法
    private static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode temp = dummy;
        while (temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;

        }
        return dummy.next;
    }


    private static ListNode initListNode() {

        ListNode l1_1 = new ListNode(1);
        ListNode l1_2 = new ListNode(2);
        ListNode l1_3 = new ListNode(3);
        ListNode l1_4 = new ListNode(4);
        ListNode l1_5 = new ListNode(5);
        ListNode l1_6 = new ListNode(6);

        l1_1.next = l1_2;
        l1_2.next = l1_3;
        l1_3.next = l1_4;
        l1_4.next = l1_5;
        l1_5.next = l1_6;
        return l1_1;
    }

    private static void outLists(ListNode result) {
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
