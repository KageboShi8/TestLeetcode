import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;
import com.sun.scenario.animation.shared.ClipEnvelope;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 287 找到数组中的重复数  本题有非常多的衍生品，甚至包括找到数组中的不重复数等。在不同给定的时间复杂度和空间复杂度的基础上有非常多的系列。
 * 最简单的莫过于暴力查找，这里就不赘述了。最好理解的在于排序，然后两两比较就好，排序一般看成nlogn的复杂度，效果已经很好了。当然还有别的思路。这题归根到底是一个
 * 查找问题，而查找问题必然考虑的方法就一定有hash，二分和双指针。这里都可以使用和考虑。二分和双指针有点难以理解，酌情理解一下。
 */

public class Solution {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        int answer = findDuplicate4(nums);
        System.out.println("answer=" + answer);
    }

    //双指针法 把数组看成链表 问题在于 如果只要有环倒是容易 需要入口，就需要多一次遍历 让slow接着绕环 fast再来一遍 这样就会在入口相见了
    private static int findDuplicate4(int[] nums) {
        int fast = 0;
        int slow = 0;
        while (true) {
            fast = nums[nums[fast]];
            
            slow = nums[slow];
            if (slow == fast) {
                fast = 0;
                while (nums[slow] != nums[fast]) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[slow];
            }
        }

    }

    //假设不让修改原数组 当然也不能O(n)辅助数组  考虑一个二分查找的思想 毕竟没有了空间 一定需要时间的
    private static int findDuplicate3(int[] nums) {
        int start = 1;
        int end = nums.length - 1;
        while (end >= start) {
            int middle = (end - start) / 2 + start;
            int count = countRange(nums, nums.length, start, middle);
            if (end == start) {
                if (count > 1) {
                    return start;
                } else break;
            }
            if (count > middle - start + 1) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }

        return -1;
    }

    private static int countRange(int[] nums, int length, int start, int end) {
        if (nums == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] >= start && nums[i] <= end) {
                count++;
            }
        }
        return count;
    }


    //由于题设给的是n+1个1~n的数字，那么 理论上应该每个位置应该对应自己的数字，一旦不对应就换，或者出错了。时间复杂度O(n),空间复杂度O(1).
    private static int findDuplicate2(int[] nums) {
//        int index = 0;
//        while (index < nums.length) {
//            if (index == nums[index]) {
//                index++;
//            } else if (nums[nums[index]] == nums[index]) {
//                return nums[index];
//            } else {
//                while ()
//            }
//        }

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                } else {
                    int temp = nums[i];
                    nums[i] = nums[temp];
                    nums[temp] = temp;
                }
            }
        }
        return 0;
    }


    //hash 使用 记住要用containsKey 以及此方法空间复杂度是O（n）,虽然时间复杂度也是O(n)
    private static int findDuplicate1(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        Map singleNum = new HashMap();
        singleNum.put(nums[0], 0);

        for (int i = 1; i < nums.length; i++) {
            if (singleNum.containsKey(nums[i])) {
                return nums[i];
            } else {
                singleNum.put(nums[i], i);
            }
        }
        return 0;
    }

    //直接排序当然是相当简单的 sort Array.sort在int时用的是快排
    private static int findDuplicate(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return 0;
    }


}
