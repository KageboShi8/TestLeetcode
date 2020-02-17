import java.util.List;

/**
 * leetcode 445 带进位的链表加法
 * 单向链表加法运算  1->2->3->4 +  3->4  =  1->2->6->8
 * 当然没有进位运算  有进位就炸裂了
 */

public class Solution {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = initList1();
        ListNode l2 = initList2();
        int count = handleList(l1, l2);
        ListNode temp = new ListNode(0);
        ListNode l2Handle = temp;
        for (int i = 0; i < Math.abs(count) - 1; i++) {
            temp.next = new ListNode(0);
            temp = temp.next;
        }
        System.out.println(count);
        if (count > 0) {
            temp.next = l2;
        } else if (count < 0) {
            temp.next = l1;
        }


        ListNode result = listSum2(l1, l2Handle);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    private static int handleList(ListNode l1, ListNode l2) {
        ListNode x1 = l1;
        ListNode x2 = l2;
        int count = 0;
        while (x1 != null && x2 != null) {
            x1 = x1.next;
            x2 = x2.next;
        }
        if (x2 == null) {
            while (x1 != null) {
                count++;
                x1 = x1.next;
            }
        }
        if (x1 == null) {
            while (x2 != null) {
                count--;
                x2 = x2.next;
            }
        }

        return count;
    }

    //直接递归相加  还是有点问题 执行结果还是不对。
    private static ListNode listSum2(ListNode l1, ListNode l2) {
        if (l1.next == null && l2.next == null) {
            return new ListNode(l1.val + l2.val);
        }

        ListNode listNode = new ListNode(l1.val + l2.val);
        System.out.println("listNode="+listNode.val);
        ListNode temp = listSum2(l1.next, l2.next);


        if (temp.val > 9) {
            temp = new ListNode(temp.val - 10);
            listNode = new ListNode(l1.val + l2.val + 1);
        }
//        System.out.println("temp="+temp.val);
//        System.out.println("listNode="+listNode.val);
        listNode.next = temp;


        l1.next.next = l1;
        l1.next = null;
        l2.next.next = l2;
        l2.next = l2;

        return listNode;

    }


    //此种方法使用的是逆序，相加，逆序的方法，重点在于链表的翻转 递归和迭代都要掌握。 本func没有进位的问题 当然迭代进位也算还行 毕竟已经逆序了 没逆序才叫难做 参见listSum2
    private static ListNode listSum(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode result = new ListNode(-1);
        l1 = reverseList2(l1);
        l2 = reverseList2(l2);

        ListNode tempL1 = l1;
        ListNode tempL2 = l2;
        ListNode tempResult = result;

        while (tempL1 != null && tempL2 != null) {
            tempResult.next = new ListNode(tempL1.val + tempL2.val);
            tempResult = tempResult.next;
            tempL1 = tempL1.next;
            tempL2 = tempL2.next;
        }
        if (tempL2 != null) {
            tempResult.next = tempL2;
        }
        if (tempL1 != null) {
            tempResult.next = tempL1;
        }
        return reverseList2(result.next);
    }

    private static ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode temp2 = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp2;
        }
        return prev;
    }

    private static ListNode reverseList2(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode temp = head.next;
        ListNode listNode = reverseList2(temp);
        temp.next = head;
        head.next = null;
        return listNode;
    }

    private static ListNode reverseList3(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode xxx = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return xxx;

    }

    private static ListNode initList2() {
        ListNode l3 = new ListNode(5);
        ListNode l4 = new ListNode(6);
        ListNode l5 = new ListNode(4);

        l3.next = l4;
        l4.next = l5;
        return l3;
    }

    private static ListNode initList1() {
        ListNode l1 = new ListNode(7);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(3);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;


        return l1;
    }
}
