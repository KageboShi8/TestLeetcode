/**
 * leetcode 152 乘积的最大子数组
 */

public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{-3,-2,-1};
        int res = maxProduct(nums);
        System.out.println(res);
    }

    private static int maxProduct(int[] nums) {
        int length = nums.length;

        int[] dpMax = new int[length];
        int[] dpMin = new int[length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        for (int i = 1; i < length; i++) {
            if (nums[i] >= 0) {
                dpMax[i] = Math.max(nums[i], dpMax[i - 1] * nums[i]);
                dpMin[i] = Math.min(nums[i], dpMin[i - 1] * nums[i]);
            } else {
                dpMax[i] = Math.max(nums[i], dpMin[i - 1] * nums[i]);
                dpMin[i] = Math.min(nums[i], dpMax[i - 1] * nums[i]);
            }
            System.out.println(dpMax[i]);
        }
        int res = dpMax[0];
        for (int j = 0; j < nums.length; j++) {
            res = Math.max(res, dpMax[j]);
        }


        return res;
    }


}
