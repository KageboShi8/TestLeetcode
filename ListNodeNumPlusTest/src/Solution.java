import java.util.Stack;

/**
 * leetcode 445 链表两数相加
 */

public class Solution {

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
        ListNode l1 = initList1();
        ListNode l2 = initList2();
//        ListNode listNode = addTwoNumbers(l1, l2);
        ListNode listNode = addTwoNumbers2(l1, l2);

//        while (stack.size()!=0){
//            System.out.println(stack.pop());
//        }
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }

    private static ListNode initList2() {
        ListNode l1 = new ListNode(7);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(6);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        return l1;
    }

    private static ListNode initList1() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(4);

        l1.next = l2;
        l2.next = l3;

        return l1;

    }

    //翻转链表法
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode reverseL1 = reverseList(l1);
        ListNode reverseL2 = reverseList(l2);

        ListNode reverseResult = new ListNode(-1);
        ListNode result = reverseResult;
        int sum;
        boolean addOne = false;
        while ((reverseL1 != null) || (reverseL2 != null)) {
            if (reverseL1.next == null && reverseL2.next != null) {
                reverseL1.next = new ListNode(0);
            }
            if (reverseL2.next == null && reverseL1.next != null) {
                reverseL2.next = new ListNode(0);
            }
            sum = (addOne) ? (reverseL1.val + reverseL2.val + 1) : (reverseL1.val + reverseL2.val);
            addOne = false;
            if (sum > 9) {
                addOne = true;
                reverseResult.next = new ListNode(sum - 10);
            } else {
                reverseResult.next = new ListNode(sum);
            }
            reverseL1 = reverseL1.next;
            reverseL2 = reverseL2.next;
            reverseResult = reverseResult.next;
        }

        if (addOne) {
            reverseResult.next = new ListNode(1);
        }
        return reverseList(result.next);
    }

    public static ListNode reverseList(ListNode l) {
        ListNode prev = null;
        ListNode curr = l;
        while (curr != null) {
            ListNode nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
        }
        return prev;
    }

    //使用单调栈的方法 非常强的尾插法的链表连接 值得学习
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack();
        Stack<Integer> stack2 = new Stack();
        ListNode answer=null;
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        Integer sum;
        while ((stack1.size() != 0 )||(stack2.size() != 0)||(carry!=0)) {
            int a=stack1.isEmpty()?0:stack1.pop();
            int b=stack2.isEmpty()?0:stack2.pop();


            sum = a+b+carry;
            carry=sum/10;
            sum=sum%10;

            ListNode node=new ListNode(sum);
            node.next=answer;
            answer=node;
        }


        return answer;

    }
}
