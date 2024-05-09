//leetcode 1049 最后一块石头的重量

public class Solution {

    public static void main(String[] args) {
        int[] stones = new int[]{2, 7, 4, 1, 8, 1};
        int res = lastStoneWeightII(stones);
        System.out.println(res);
    }

    //要想象成01背包问题
    private static int lastStoneWeightII(int[] stones) {
        int len = stones.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += stones[i];
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int i = 0; i < len; i++) {
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }


        return sum-dp[target]*2;
    }
}
