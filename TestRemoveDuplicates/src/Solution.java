import java.util.Arrays;

/**
 * leetcode 26 删除排序数组的重复项
 * 题目很简单，ac也很简单，问题是2是标答，一是我写的，足见我的编码水平有多差。
 */

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int result = removeDuplicates1(nums);
        System.out.println(result);
    }

    private static int removeDuplicates1(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1) {
            return n;
        }
        int i = 0;
        int j = 1;
        int duplicateNum = nums[i];
        while (i < n && j < n) {
            if (nums[j] == duplicateNum) {

            } else {
                i++;
                nums[i] = nums[j];
                duplicateNum = nums[j];
            }
            j++;
        }
        System.out.println(Arrays.toString(nums));
        return i + 1;
    }

    private static int removeDuplicates2(int[] nums) {
        if (nums.length == 0) return 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[index] != nums[i]) {
                index++;
                nums[index] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return index + 1;
    }
}
