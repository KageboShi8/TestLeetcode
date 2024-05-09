/**
 * leetcode 516 最长回文子序列  这题和leetcode5的区别在于这是子序列，是可以抽调一些字母的，子串的话就得连续完整
 */


public class Solution {
    public static void main(String[] args) {
        String s = "xaabacxcabaaxcabaax";
        int res = longestPalindromeSubseq1(s);
        System.out.println(res);
    }

    //子序列
    private static int longestPalindromeSubseq(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }


        return dp[0][length - 1];
    }

    //子串
    private static int longestPalindromeSubseq1(String s) {
        int[][] dp = new int[s.length()][s.length()];
        int max = 0;
        int start = 0;
        int end = 0;
        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j) && (dp[i + 1][j - 1] == (j - i - 1) || j - i < 3)) {
                    dp[i][j] = j - i + 1;
                } else {
                    dp[i][j] = 0;
                }
                System.out.println("dp" + i + j + "=" + dp[i][j]);
                if (dp[i][j] > max) {
                    start = i;
                    end = j;
                    max = dp[i][j];
                }
            }
        }
        String res = s.substring(start, end + 1);
        System.out.println(res);
        return max;
    }
}
