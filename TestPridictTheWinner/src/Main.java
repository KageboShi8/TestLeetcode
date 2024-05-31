/**
 * leetcode 486 预测赢家
 */

public class Main {
    public static void main(String[] args) {

        int[] nums = new int[]{1, 5, 233, 7};
        boolean res = predictTheWinner(nums);

        System.out.println(res);
    }

    //这里的转移方程的符号有点难想，不过最难的其实是dp的定义，这里dp定义是当前玩家对于下一个玩家的分数差值
    //所以答案就是0~n-1的先手先拿的分数差值
    private static boolean predictTheWinner(int[] nums) {
        if (nums.length == 1) return true;
        int[][] dp = new int[nums.length][nums.length];
        dp[0][0] = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < dp.length; j++) {
                if (i == j) {
                    dp[i][j] = nums[i];
                } else if (i < j) {
                    dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
                }
            }
        }


        return dp[0][nums.length - 1] >= 0;
    }
}