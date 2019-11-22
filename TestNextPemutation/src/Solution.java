import java.util.Arrays;

/**
 * leetcode 31 下一个排列
 * 本题是一个思路题，题意是找到下一个字典序比原来数大的排列，问题就是如何找到这个正好大一点点的数。
 * 思路在于从低位向高位查找，直到低位比高位数字大，就产生了逆序的可能。eg：【3，2，1】 高位永远比低位大，所以，没有下一个排列
 * 反过来【2，3，1】，在2，3之间有一个反序，那么就必然会有比他大的排列。那么下面就是找到下一个，因为，很明显，直接反序这两个数得到的并不是下一个
 * 所以，再找到切入点之后，要将切入点之后的数据整个升序排列。这种情况，正好也涵盖了没有下一个排列时的升序情况。
 * 当然本题还有一个难点，要原地修改，只能使用常数的额外空间。
 * 法一提供了非常顺序的逻辑，先找到交换点，排序交换点以后的元素，再遍历交换点以后的元素，找到正好大于交换点前一个数的位置，交换他们即可。写起来很简单。
 * 其实本题的复杂度来源于排序的复杂度.
 */

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 4, 6, 7, 4, 2};
        int[] result = nextPermutation1(nums);
        System.out.println(Arrays.toString(result));
    }

    private static int[] nextPermutation1(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i != 0) {
                if (nums[i] <= nums[i - 1]) {
                    continue;
                } else {
                    partialSort1(nums, i);
                    swap1(nums, i);
                    break;
                }
            } else if (i == 0) {
                partialSort1(nums, i);
            }
        }

        return nums;
    }

    private static void swap1(int[] nums, int i) {
        int temp = 0;
        for (int j = i; j < nums.length; j++) {
            if (nums[i - 1] < nums[j]) {
                temp = nums[i - 1];
                nums[i - 1] = nums[j];
                nums[j] = temp;
                break;
            }
        }
    }

    private static void partialSort1(int[] nums, int i) {
        int temp = 0;
        for (int m = i; m < nums.length - 1; m++) {
            for (int n = m + 1; n < nums.length; n++) {
                if (nums[m] > nums[n]) {
                    temp = nums[m];
                    nums[m] = nums[n];
                    nums[n] = temp;
                }
            }
        }
    }


}
