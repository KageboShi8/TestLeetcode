/**
 * leetcode 518  零钱兑换2
 */

public class Solution {
    public static void main(String[] args) {
        int amount = 5;
        int[] input = new int[]{1, 2, 5};
        int res = change(amount, input);
        System.out.println(res);
    }

    private static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }

        return dp[amount];
    }


}
