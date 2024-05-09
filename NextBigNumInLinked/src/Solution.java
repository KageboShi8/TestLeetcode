import java.util.Arrays;
import java.util.Stack;

/**
 * 1019 链表中下一个更大的数字
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
        ListNode l=initList();
        int[] ints = nextLargerNodes(l);
        System.out.println(Arrays.toString(ints));
    }

    private static ListNode initList() {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(7);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        return l1;
    }

    //使用翻转链表加上单调栈法，条件判断我做的很差。
    public static int[] nextLargerNodes(ListNode head) {
        if(head==null){
            return null;
        }
        if(head.next==null){
            return new int[1];
        }
        ListNode prev=null;
        ListNode curr=head;
        int length=0;
        while(curr!=null){
            length++;
            ListNode nxt=curr.next;
            curr.next=prev;
            prev=curr;
            curr=nxt;
        }
        int[] ans=new int[length];
        Stack<Integer> vector=new Stack();
        int index=0;
        while (index<length){
            System.out.println("index="+index);
            System.out.println("size="+vector.size());
            if(vector.size()==0){
                ans[length-index-1]=0;
                vector.push(prev.val);
                prev=prev.next;
                index++;
            }else{
                Integer v=vector.peek();
                System.out.println("v="+v+"     prev.val="+prev.val);

                if(v>prev.val){
                    vector.push(prev.val);
                    ans[length-index-1]=v;
                    prev=prev.next;
                    index++;
                }else{
                    vector.pop();
                    continue;
                }
            }

        }


        return ans;
    }
}
