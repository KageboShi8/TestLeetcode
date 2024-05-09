/**
 * leetcode 221 最大正方形
 */
public class Solution {

    public static void main(String[] args) {
        char[][] input = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        int res = maximalSquare(input);
        System.out.println(res);
    }

    //这里的关键是想通本格的1和0 与其周边的有什么关系 受到怎样的约束 和1277相似
    private static int maximalSquare(char[][] matrix) {
        int col = matrix.length;
        int row = matrix[0].length;
        int[][] dp = new int[col][row];
        dp[0][0] = 0;
        int res = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    }
                }
                res = Math.max(res, dp[i][j]);
            }
        }


        return res * res;
    }
}
