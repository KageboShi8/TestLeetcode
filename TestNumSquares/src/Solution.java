/**
 * leetcode 279 完全平方数
 */

public class Solution {
    public static void main(String[] args) {
        int res = numSquare(13);
        System.out.println(res);
    }

    private static int numSquare(int n) {
        int length = (int) Math.sqrt(n);
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[i]=Integer.MAX_VALUE;
        }
        dp[0]=0;
        for (int i = 1; i <=length; i++) {
            for (int j = 0; j <= n; j++) {
                if (i * i <= j) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
        }

        return dp[n];
    }



}
