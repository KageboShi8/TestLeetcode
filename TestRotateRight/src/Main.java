/**
 * leetcode 61 旋转链表
 */

public class Main {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode head = generateList();
        rotateRight(head, 2);
    }

    //暴力解法 显然只有这一种方法，连环再断开
    private static ListNode rotateRight(ListNode head, int k) {
        ListNode temp = head;
        int n = 1;
        while (temp.next != null) {
            temp = temp.next;
            n++;
        }
        int offset = n - (k % n);
        if (offset == n) {
            return head;
        }
        temp.next = head;

        while (offset > 0) {
            temp = temp.next;
            offset--;
        }
        ListNode res = temp.next;
        temp.next = null;
        return res;

    }

    private static ListNode generateList() {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        head.next = head2;
        ListNode head3 = new ListNode(3);
        head2.next = head3;
        ListNode head4 = new ListNode(4);
        head3.next = head4;
        ListNode head5 = new ListNode(5);
        head4.next = head5;


        return head;
    }
}