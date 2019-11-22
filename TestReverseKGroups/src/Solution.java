/**
 * leetcode 25 k个一组翻转链表
 * 非常难的题目 联动 92，206.递归考验所谓的子问题分解。要注意不要在意递归的细节，找准递归的出入口和整体的功能。迭代则是很多细节的考量。链表题的麻烦之处就在于此。迭代细节多，递归
 * 写法复杂。
 */

public class Solution {

    private static ListNode node;

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        ListNode l = initListNode();
        ListNode result = reverseKGroup1(l, 3);
//        ListNode result = reverse(l, l.next.next.next.next.next.next);
        outLists(result);
    }

    //递归
    private static ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null) return head;
        ListNode a = head;
        ListNode b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) return head;
            b = b.next;
        }
        ListNode newHead = reverse(a, b);
        a.next = reverseKGroup1(b, k);
        return newHead;
    }

    //交换同一链表两节点间的所有数据
    private static ListNode reverse(ListNode a, ListNode b) {
        if (a.next == b) return a;
        ListNode p = reverse(a.next, b);
        a.next.next = a;
        a.next = null;
        return p;
    }


    //迭代
    private static ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode temp = dummy;
        ListNode[] listNodes = new ListNode[k];
        for (int i = 0; i < k; i++) {
            listNodes[i] = temp;
            temp = temp.next;
        }
        ListNode temp2 = dummy;
        while (checkListsNotNull(listNodes)) {
            temp2.next = listNodes[k - 1];
            listNodes[0].next = listNodes[k - 1].next;
            for (int i = k - 1; i > 0; i--) {
                listNodes[i].next = listNodes[i - 1];
            }
            temp2 = listNodes[0];
        }
        return dummy.next;


    }

    private static boolean checkListsNotNull(ListNode[] listNodes) {
        for (int i = 0; i < listNodes.length; i++) {
            if (listNodes[i] == null) {
                return false;
            }
        }
        return true;
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
