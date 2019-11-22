/**
 * leetcode 92 反转链表2  反转从m到n的链表
 * 法1用的是迭代 和206反转链表其实是一个方法。不过，这里有头尾的重连接问题。整体翻转，直接交换节点就行，这里要先循环到位置，然后交换。最后将头尾重连解，所以
 * 需要两个局部变量记载其头尾，就这一点点区别而已。我对于编码还是没什么信息，或者说，能力不够。
 * 法2用的是递归。简直是神仙写法。我只能说一点皮毛。首先是借鉴206的整体翻转思路。然后是先考虑什么时候开始递归的问题，于是将整个问题化成两部分，一部分是翻转前段
 * 的操作，其实就是将两端接上。然后是翻转后段，此处有一个翻转前n个的子问题。作为一个递归的拆解。反转前n个其实也和整体反转差不多，只是在最后有一个节点的存储和链接问题
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
        ListNode result = reverseBetween2(l, 2, 4);
        outLists(result);

    }

    //递归
    private static ListNode reverseBetween2(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        ListNode p = reverseBetween2(head.next, m - 1, n - 1);
        head.next = p;
        return head;
    }

    private static ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            node = head.next;
            return head;
        }
        ListNode p = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = node;
        return p;
    }

    //迭代
    private static ListNode reverseBetween1(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        ListNode curr = head;
        ListNode prev = null;
        while (m > 1) {
            prev = curr;
            curr = curr.next;
            m--;
            n--;
        }

        ListNode con = prev;
        ListNode tail = curr;
        ListNode third = null;
        while (n > 0) {
            third = curr.next;
            curr.next = prev;
            prev = curr;
            curr = third;
            n--;
        }
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }
        tail.next = curr;
        return head;
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
