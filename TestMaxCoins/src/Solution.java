/**
 * leetcode 312 戳气球
 */

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 5, 8};
        int res = maxCoins(nums);
        System.out.println(res);
    }

    private static int maxCoins(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length + 2];
        dp[0] = 1;
        dp[length + 1] = 1;


        return dp[length];
    }
}
