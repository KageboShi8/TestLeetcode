/**
 * leetcode 21 合并两个有序链表
 * 合并两个链表 其实没有什么太多的操作，注意链表的构造，注意链表要使用temp进行备份，注意节点的移动。
 * 迭代法很简单，主要是编码技巧的问题，递归的方法其实从道理上说也很简单，也是编码技巧的问题。不过我不太会。
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
        ListNode listNode1 = initListNode1();
        ListNode listNode2 = initListNode2();

        ListNode result = mergeTwoLists(listNode1, listNode2);
        outListNode(result);
    }

    //迭代
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode temp = result;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                temp.next = l2;
                l2 = l2.next;
            } else {
                temp.next = l1;
                l1 = l1.next;
            }
            temp = temp.next;
        }
        temp.next = l1 == null ? l2 : l1;
        return result.next;
    }

    //递归
    private static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

    private static void outListNode(ListNode listNode) {
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    private static ListNode initListNode2() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;

        return node1;
    }

    private static ListNode initListNode1() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;

        return node1;
    }


}
