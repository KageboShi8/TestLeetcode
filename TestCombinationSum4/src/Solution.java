/**
 * leetcode 377  组合总和
 */

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int target = 4;
        int res = combinationSum(nums, target);
        System.out.println(res);
    }

    private static int combinationSum(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
//        for (int i = 1; i <= target; i++) {
//            for (int j = 0; j < nums.length; j++) {
//                if (i >= nums[j]) {
//                    dp[i] = dp[i] + dp[i - nums[j]];
//                }
//            }
//        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j>=nums[i]){
                    dp[j]=dp[j]+dp[j-nums[i]];
                }
            }
        }
        return dp[target];
    }
}
