/**
 * leetcode 64 最小路劲和
 */

public class Solution {
    public static void main(String[] args) {
        int[][] inputs = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int res = minPathSum(inputs);
        System.out.println(res);
    }

    private static int minPathSum(int[][] grid) {
        int height = grid.length;
        int row = grid[0].length;
        System.out.println("h="+height+"  r="+row);
        int[][] dp = new int[height][row];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < height; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int j = 1; j < row; j++) {
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        }
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < row; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1])+grid[i][j];
            }
        }


        return dp[height - 1][row - 1];
    }
}
