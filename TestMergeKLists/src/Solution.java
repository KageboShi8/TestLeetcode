import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * leetcode 23 合并k个排序链表
 * 法一基本就是i暴力搜索，有非常糟糕的复杂度，法二用的是java优先队列，自定义了比较器，内部实现是小顶堆，在数据结构时要仔细研读一下堆
 * 法三是分治法，基本和归并排序一个思路。可是我递归编码能力太差。还需好好练习。
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
        ListNode[] lists = initKLists();
        ListNode result = mergeKLists3(lists);
        outLists(result);
    }


    //分治法 应该是很简单的，可是递归学的太差了
    private static ListNode mergeKLists3(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private static ListNode mergeKLists(ListNode[] lists, int l, int r) {
        if (lists.length == 0) return null;
        if (l == r) {
            return lists[l];
        }
        int mid = (l + r) / 2;
        ListNode l1 = mergeKLists(lists, l, mid);
        ListNode l2 = mergeKLists(lists, mid + 1, r);
        return merge(l1, l2);

    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }

    }


    //优先队列 优先队列用的小顶堆，有的好研究了。不过从优先队列的角度来说，其实就是怎样用优先队列的方式排序，以及取出的问题
    //这个方案的复杂度只取决于java优先队列的复杂度。这就和堆排序息息相关了。
    private static ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val) {
                    return -1;
                } else if (o1.val == o2.val) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            p.next = queue.poll();
            p = p.next;
            if (p.next != null) {
                queue.add(p.next);
            }
        }
        return dummy.next;
    }


    //逐一比较，有非常糟糕的运行时间
    private static ListNode mergeKLists1(ListNode[] lists) {
        ListNode result = new ListNode(-1);
        ListNode temp = result;
        while (checkListNodeEnd(lists)) {
            int minIndex = getMinIndex(lists);
            temp.next = lists[minIndex];
            lists[minIndex] = lists[minIndex].next;
            temp = temp.next;
        }
        return result.next;


    }

    private static int getMinIndex(ListNode[] lists) {
//        ListNode temp = lists[0];
//        for (int i = 0; i < lists.length; i++) {
//            if (lists[i]!=null){
//                temp=lists[i];
//            }
//        }
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                min = lists[i].val;
            }
        }
        int index = 0;

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                index = lists[i].val > min ? index : i;
                min = lists[i].val > min ? min : lists[i].val;
                //                temp = lists[i].val > temp.val ? temp : lists[i];
            }
        }
        return index;
    }

    private static boolean checkListNodeEnd(ListNode[] lists) {
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                return true;
            }
        }
        return false;
    }

    private static ListNode[] initKLists() {
        ListNode[] lists = new ListNode[3];

        ListNode l1_1 = new ListNode(1);
        ListNode l1_2 = new ListNode(4);
        ListNode l1_3 = new ListNode(5);
        l1_1.next = l1_2;
        l1_2.next = l1_3;

        ListNode l2_1 = new ListNode(1);
        ListNode l2_2 = new ListNode(3);
        ListNode l2_3 = new ListNode(4);
        l2_1.next = l2_2;
        l2_2.next = l2_3;

        ListNode l3_1 = new ListNode(2);
        ListNode l3_2 = new ListNode(6);
        l3_1.next = l3_2;

        lists[0] = l1_1;
        lists[1] = l2_1;
        lists[2] = l3_1;

        return lists;
    }

    private static void outLists(ListNode result) {
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
