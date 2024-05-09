/*
    leetcode 300 最长递增子序列
 */

public class Solution {

    public static void main(String[] args) {
        int[] input = {0, 1, 0, 3, 2, 3};
        int res = lengthOfLIS1(input);
        System.out.println(res);
    }

    private static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    private static int lengthOfLIS1(int[] num) {
        int length = num.length;
        int[] dp = new int[length];
        dp[0] = 1;
        int max = dp[0];
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (num[i]>num[j]){
                    dp[i]=Math.max(dp[j]+1,dp[i]);
                }
            }
            max=Math.max(max,dp[i]);
        }

        return max;
    }

}
