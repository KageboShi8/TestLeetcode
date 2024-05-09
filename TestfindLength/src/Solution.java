/**
 * leetcode 718 最长重复子数组
 */


public class Solution {
    public static void main(String[] args) {
        int[] input1 = {1, 2, 3, 2, 1};
        int[] input2 = {3, 2, 1, 4, 7};
        int res = findLength(input1, input2);
        System.out.println(res);
    }

    private static int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = 0;
        }
        int res = 0;
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }


        return res;
    }
}
