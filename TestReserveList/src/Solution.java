/**
 * leetcode 206 翻转链表
 * 翻转链表看起来是个很简单的题，其中其实可以挖掘的地方很多。
 * 先说说递归，递归从本质上来说其实就是打乱了整个逻辑的执行顺序，本来若是迭代，执行顺序一般来说是从前往后。从这个题目的角度来说，也就是从head->tail
 * 问题在于，单链表一旦将原链条切断，就无法复原了，可以从前溯得到后续，而后续得到前溯就很难了，所以，递归打乱了这个顺序，在不影响链条本身的前提下反转了整个运行逻辑，
 * 让其有一个tail->head 的运行逻辑，于是乎，对于递归来说，结束调节就显得异常重要，那就是所谓的一切递归的起源。所以，单链表递归用的很多。
 * 说完递归，说对单链表的理解，链表最重要的特性就是所谓的node，对于node的理解其实就是链表的一切。所谓的node其实也是对象，和数组不同，数组的一个位置对应的只有一个值
 * 而链表，包括后述的树，重点就在于节点，这里链表的反转其实就是所谓对象内属性的修改，而这个属性，恰好又是下一个node的地址。java中其实对此有所混淆的，因为对应的对象内
 * 的属性，其实是值和地址，只是这个地址里又包含了值和地址，依次嵌套而已。于是乎，递归解链表的关键一般来说不在值的改变，而在地址的修改，也就是所谓，链条的切断和新建。
 * 每一个递归的子单元，一定包含着原链条的切断和新链条的建立，当然，也要考虑是否有边界问题。另外多说一句，递归子单元的return，对应着每一次递归的结论，而总的结论，一般来说
 * 就是子单元递归到最后一次（一般是整个程序开始的那次的return）的返回值。而边界条件的返回值，一般来说是整个递归的开始条件。
 * 再看迭代方法，其实方法也很简单，基本可以看作是两数交换的变体。若然不是在链表这个大环境下，简直简单无比。具体步骤我都不齿于去写。无非是，先把自己的下一个位置存下来，拆掉
 * 自己本来的指向，将其指向前置位，然后前置位移到现在这个位置，将现在的位置移到下一个位置，就完工了。简单无比，我却不会。不会的理由无非是对链表理解不够深入而已。
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
        ListNode listNode = initListNode();
        ListNode result = reserveList2(listNode);
        outLists(result);
    }

    //    迭代
    private static ListNode reserveList2(ListNode head) {
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


    //递归
    private static ListNode reserveList1(ListNode head) {
        if (head.next == null || head == null) {
            return head;
        }
        ListNode p = reserveList1(head.next);
        head.next.next = head;
        head.next = null;
        return p;
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
