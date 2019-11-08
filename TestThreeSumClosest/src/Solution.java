import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * leetcode 16 最接近的三数和
 * 和前一题完全一样 双指针 减少一层的复杂度 得到n^2的复杂度 无甚好说，注意边界和审题就行
 */


public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        int target = 1;
        int result = threeSumClosest(nums, target);
        System.out.println("result=" + result);
    }

    private static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        if (nums.length < 3) return 100;
        int result = nums[0] + nums[1] + nums[2];
        for (int k = 0; k < nums.length - 2; k++) {
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int i1 = nums[i] + nums[j] + nums[k];
                if (i1 < target) {
                    i++;
                    if (Math.abs(i1 - target) < Math.abs(result - target)) {
                        result = i1;
                    }
                }
                if (i1 > target) {
                    j--;
                    if (Math.abs(i1 - target) < Math.abs(result - target)) {
                        result = i1;
                    }
                }
                if (i1 - target == 0) {
                    return i1;
                }
            }
        }
        return result;
    }
}
