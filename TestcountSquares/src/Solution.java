/**
 * leetcode 1277 统计全为1的正方形子矩阵
 */

public class Solution {

    public static void main(String[] args) {
        int[][] input = {{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        int res = countSquares(input);
        System.out.println(res);
    }

    //要注意，所有dp全加进来就是正方形的总数，这点要想通，其他都和221题一样思路
    private static int countSquares(int[][] matrix) {
        int col = matrix.length;
        int row = matrix[0].length;
        int[][] dp = new int[col][row];
        dp[0][0] = 0;
        int res = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    }
                } else {
                    dp[i][j] = 0;
                }
                System.out.println("dp"+i+" "+j+ "="+ dp[i][j]);
                res += dp[i][j];

            }
        }
        return res;
    }
}
