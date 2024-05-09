/**
 * 343 整数拆分
 */

public class Solution {
    public static void main(String[] args) {
        int input = 2;
        int res = integerBreak(input);
        System.out.println(res);
    }

    private static int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, j * dp[i - j]));
            }
        }

        return dp[n];
    }


}
