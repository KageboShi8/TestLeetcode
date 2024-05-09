/**
 * leetcode 1155 制筛子的方法
 */

public class Solution {
    public static void main(String[] args) {
        int k = 30;
        int target = 500;
        int n = 30;
        int res = numRollsToTarget(n, k, target);
        System.out.println(res);
    }

    //这里要注意取模和科学技术法的问题
    private static int numRollsToTarget(int n, int k, int target) {
        int mod = (int) 1e9 + 7;
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                for (int l = 0; l <= k; l++) {
                    if (j >= l)
                        dp[i][j] = dp[i][j] + dp[i - 1][j - l] % mod;
                }
            }
        }
        return dp[n][target];


    }


}
