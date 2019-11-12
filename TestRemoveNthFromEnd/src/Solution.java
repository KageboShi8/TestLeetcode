import java.util.LinkedList;

/**
 * leetcode 19 删除链表的倒数第n个节点
 * 链表的构造愈发精纯，两次遍历很简单，一次遍历有一点小技巧，其实也是双指针的技巧而已，记住就行
 * 另外，链表题一般都需要虚的头来解决边界问题。
 */

public class Solution {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }

    public static void main(String[] args) {
        ListNode linkedList = initNode();
        ListNode result = removeNthFromEnd1(linkedList, 2);
        outNode(result);
    }

    private static void outNode(ListNode result) {
        ListNode current = result;
//        for (int i = 0; i < current.length(current); i++) {
//            System.out.println(current.val);
//            current=current.next;
//        }
//
        while (current != null) {
            System.out.println(current.val);
            current = current.next;
        }
    }

    //两次遍历
    private static ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 0;
        ListNode virtualHead = new ListNode(0);
        virtualHead.next = head;
        ListNode current = virtualHead;
        while (current.next != null) {
            current = current.next;
            count++;
        }
        current = virtualHead;
        if (count >= n && n > 0 && count > 0) {
            for (int i = 0; i < count - n; i++) {
                current = current.next;
            }
            current.next = current.next.next;

        } else {
            return null;
        }
        return virtualHead.next;
    }

    //一次遍历
    private static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode virtualHead = new ListNode(0);
        virtualHead.next = head;
        ListNode p = virtualHead;
        ListNode q = virtualHead;
        for (int i = 0; i < n; i++) {
            q = q.next;
        }
        while (q.next != null) {
            p = p.next;
            q = q.next;
        }
        p.next = p.next.next;
        return virtualHead.next;
    }


    private static ListNode initNode() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        return node1;
    }


}

