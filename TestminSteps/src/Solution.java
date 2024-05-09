/**
 * leetcode 650 只有两个键帽的键盘
 */

public class Solution {

    public static void main(String[] args) {
        int input = 9;
        int res = minSteps(input);
        System.out.println(res);
    }
    //方法是动态规划，其他事真的没搞明白吧
    private static int minSteps(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i < n + 1; i++) {
            //这里应该使用max_value 但是会有溢出的问题，所以暂时减一
            dp[i] = Integer.MAX_VALUE - 1;
            for (int j = 1; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], Math.min(dp[i / j] + j, dp[j] + i / j));
                }
            }
        }
        return dp[n];
    }
}
