/**
 * leetcode 2 Add Two Numbers
 * 此题没有什么算法复杂度的问题，看似是一个十进制加法，其实最后落到实处的是关于链表的理解，此处提供了java写法，vscode中还提供了c语言的实现。
 * c语言比java有着很明显的内存管理优势，但在此题中，java比c的运行速度要快的多。
 * 方法1使用的是循环迭代，方法2使用的是递归，递归占用大量内存，不过在运行速度上却非常快，可能是由于复杂度被展开，和迭代已经拥有一样的时间复杂度了。
 * 递归在思路上非常好理解，主要的是尾部控制，迭代的话理解难一点，不过，尾部控制简单一点。
 */
public class AddTwoNumber {
    public static void main(String[] args) {

    }

    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        int sum = 0;
        int carry = 0;
        ListNode currentNode = new ListNode(0);
        ListNode result = currentNode;


        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            sum = x + y + carry;
            carry = sum / 10;
            currentNode.next = new ListNode(sum % 10);
            currentNode = currentNode.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            currentNode.next = new ListNode(carry);
        }

        return result.next;
    }

    public ListNode add(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            return carry == 0 ? null : new ListNode(carry);
        }
        if (l1 == null && l2 != null) {
            l1 = new ListNode(0);
        }
        if (l2 == null && l1 != null) {
            l2 = new ListNode(0);
        }
        int sum = l1.val + l2.val + carry;
        ListNode result = new ListNode(sum % 10);
        result.next = add(l1.next, l2.next, sum / 10);
        return result;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        return add(l1, l2, 0);
    }
}
