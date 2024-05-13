/**
 * leetcode 312 戳气球
 * 要尝试去理解转移方程和循环的顺序，也就是怎样获得前面已经有了的状态，毕竟dp是一个用已有的状态获得未有的状态的递推算法，
 * 所以循环的方向一定是从已有到未有。并不一定使从小到大，要看各个参数的需要。
 */

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 5, 8};
        int res = maxCoins(nums);
        System.out.println(res);
    }

    private static int maxCoins(int[] nums) {
        int length = nums.length;
        int[][] dp = new int[length + 2][length + 2];
        int[] num = new int[length + 2];
        num[0] = 1;
        num[length + 1] = 1;
        for (int i = 1; i < length + 1; i++) {
            num[i] = nums[i - 1];
        }

        for (int i = length; i >= 0; i--) {
            for (int j = i + 1; j < length + 2; j++) {
                for (int k = i+1; k < j; k++) {
                    dp[i][j] =Math.max(dp[i][j], dp[i][k] + dp[k][j] + num[k] * num[j] * num[i]);
                }
            }
        }


        return dp[0][length + 1];
    }
}
