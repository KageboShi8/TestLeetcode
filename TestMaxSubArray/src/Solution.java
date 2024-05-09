/**
 * leetcode 53 最大连续子数组和
 * 留了一个分治法的没做
 */


public class Solution {
    public static void main(String[] args) {
        int[] input = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = maxSubArray(input);
        System.out.println(res);
    }

    private static int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            System.out.println("dp"+i+"="+dp[i]);

            max=Math.max(max,dp[i]);
        }
        return max;
    }


}
