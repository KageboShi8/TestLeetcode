import javax.swing.plaf.TextUI;

/**
 * leetcode 44 wildcardMatching
 * 本题是包含？和*的通配符匹配问题。？匹配任何单个字符，*匹配任意字符串（包括空字符串）
 * 此题和第十题有一点不同。不同点在于所谓的自动机状态的构建问题。本题的方向一直是向后的，对于p来说，没有回溯的需求，自动机方向一直向后，只是在紫装要有*时，在判断*的
 * 势力范围时，有短暂的向后，而第十题，是要判断后一个状态的情况，才能得到前一个的判断结论，所以，回溯法，在第十题就很有用，也非常浅显，而本题则适合直接的有限机，更加直观。
 * 当然，动态规划两者都可以（我都不会）
 */


public class Solution {
    public static void main(String[] args) {
        String s = "ab";
        String p = "?*";

        boolean result = isMatch3(s, p);
        System.out.println("result=" + result);
    }

    //递归剪枝
    //这个方法会超时。
    private static boolean isMatch2(String s, String p) {
        return matchHelper(s, p, 0, 0);
    }

    private static boolean matchHelper(String s, String p, int i, int j) {
        if (i == s.length()) {
            return j == p.length() || (p.charAt(j) == '*' && matchHelper(s, p, i, j + 1));
        }
        if (j == p.length()) {
            return false;
        }
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            return matchHelper(s, p, i + 1, j + 1);
        }
        if (p.charAt(j) == '*') {
            if (matchHelper(s, p, i + 1, j)) {
                return true;
            }
            while (j < p.length() && p.charAt(j) == '*') {
                j++;
            }
            if (matchHelper(s, p, i, j)) {
                return true;
            }
        }
        return false;
    }

    //动态规划
    private static boolean isMatch1(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 1];
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') && dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    //有限状态的自动机  关于自动机理论 可以再聊，过于深入数学了
    private static boolean isMatch3(String s, String p) {
        int m = s.length();
        int n = p.length();
        int i = 0;
        int j = 0;
        int iStar = -1;
        int jStar = -1;
        while (i < m) {
            if (j < n && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < n && p.charAt(j) == '*') {
                jStar = j;
                j++;
                iStar = i;
            } else if (iStar >= 0) {
                j = jStar++;
                iStar++;
                i = iStar;
            } else return false;
        }
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }
        return j == p.length();
    }
}
