/**
 * leetcode 63 有障碍物的路径总数
 */
public class Solution {

    public static void main(String[] args) {
        int[][] obstacleGrid = new int[][]{{0, 0}, {1, 1}, {0, 0}};
        int res = uniquePathsWithObstacles(obstacleGrid);
        System.out.println(res);
    }

    //有障碍物版本的路径数 要注意初始条件的设定
    private static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int height = obstacleGrid.length;
        int width = obstacleGrid[0].length;
        System.out.println(height);
        System.out.println(width);
        int[][] dp = new int[height][width];
        for (int i = 0; i < width; i++) {
            dp[0][i] = 1;
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = 0;
                break;
            }
        }
        for (int i = 0; i < height; i++) {
            dp[i][0] = 1;
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
                break;
            }
        }
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                }
                System.out.println("dp" + i + j + "=" + dp[i][j]);

            }
        }
        return dp[height - 1][width - 1];
    }

    //没障碍物版本的路径数
    private static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    //数组版本的路径数
    private static int uniquePaths(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        int[][] dp = new int[height][width];
        dp[0][0] = 1;
        for (int i = 0; i < width; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < height; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[height - 1][width - 1];
    }
}
