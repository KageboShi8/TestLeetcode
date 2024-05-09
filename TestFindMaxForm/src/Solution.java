import java.util.Arrays;

/**
 * leetcode 474 一和零
 */
public class Solution {
    public static void main(String[] args) {
        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        int res = findMaxForm(strs, m, n);
//        int res1 = findMaxFormM(new int[]{1, 3, 2, 1}, 5);
        System.out.println(res);
    }

    private static int findMaxForm(String[] strs, int m, int n) {
        int[] ones = arrayChange(strs, '1');
        int[] zeros = arrayChange(strs, '0');
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 1; i <= strs.length; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j > zeros[i - 1] && k > ones[i - 1]) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeros[i - 1]][k - ones[i - 1]] + 1);
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    private static int[] arrayChange(String[] strs, char target) {
        int count = 0;
        int[] res = new int[strs.length];
        for (int i = 0; i < strs.length; i++
        ) {
            count = 0;
            char[] chars = strs[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == target) {
                    count++;
                }
            }
            res[i] = count;
        }


        return res;
    }

    //设根本不需要两个限制，如果只有一个限制，比如只有m 解法如下：
    private static int findMaxFormM(int[] nums, int m) {
        int[][] dp = new int[nums.length + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 0; i <= nums.length; i++) {
            for (int j = 0; j <= m; j++) {
                if (j > nums[i]) {
//                    dp[i][j]+=dp[i-1][j-nums[i]];
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + 1);
                }
            }
        }
        return dp[nums.length][m];
    }
}
