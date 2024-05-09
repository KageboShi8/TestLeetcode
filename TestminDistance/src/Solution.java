/*
leetcode 72  编辑距离 将word1转换为word2所需要的最小操作数
 */
public class Solution {

    public static void main(String[] args) {
        String input1 = "horse";
        String input2 = "ros";
        int res = minDistance(input1, input2);
        System.out.println(res);
    }
//仍然是双串dp问题，和走棋盘差不多。重点是当char不一样时，需要考虑插入，替换，删除三种不同情况的dp[i][j]和前置的dp的关系，
    private static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1 + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < len2 + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[len1][len2];
    }
}
