import java.util.HashMap;

/**
 * leetcode 141 链表有环
 * 很好理解的题目， 双指针或者hashmap(set的判重也是hash实现)
 */

public class Solution {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode head = initListNode();
        boolean b = hasCycle1(head);
        System.out.println(b);
    }

    //双指针法
    private static boolean hasCycle1(ListNode head) {
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode p1=dummy;
        ListNode p2=dummy;
        while (p2.next!=null&&p2.next.next!=null){
            p1=p1.next;
            p2=p2.next.next;
            if (p1.equals(p2)){
                return true;
            }
        }
        return false;
    }

    //hash法
    private static boolean hasCycle(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (dummy.next != null) {
            if (map.containsKey(dummy.next.hashCode())) {
                return true;
            } else {
                map.put(dummy.next.hashCode(), dummy.next.val);
                dummy = dummy.next;
            }
        }
        return false;
    }




    private static ListNode initListNode() {
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(4);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        return head;
    }
}
