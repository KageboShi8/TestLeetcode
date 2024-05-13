/*
    leetcode 375 猜数字大小2
 */

public class Solution {

    public static void main(String[] args) {
        int res = getMoneyAmount(10);
        System.out.println(res);
    }

    private static int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i > 0; i--) {
            for (int j = i + 1; j <= n; j++) {
                dp[i][j]=Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(Math.max(dp[i][k - 1], dp[k + 1][j]) + k, dp[i][j]);
                }
            }
        }

        return dp[1][n];
    }
}
