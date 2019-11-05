/**
 * leetcode 10 pattern match
 * 联动 leetcode 44 wildcard match 那题要简单一点  剪枝留待后文
 * 这种正则题目后面应该还有很多，此题是klanee星号题。从编译原理的角度来说，都是模式匹配的题目，从动态规划的角度来说，都是memo型。
 * 所以这种题目从思路角度来说，基本的一定是三种方法，一是构建有限状态自动机（自动机是真的难，留给后面再说吧），二是动态规划，重点在于转移方程，三则是非常明显的递归回溯，这种题目
 * 递归的思路也太好理解了，重点在于边界控制的问题。
 */

public class Solution {

    public static void main(String[] args) {
        String s = "mississippi";
        String pattern = "mis*is*p*";
        boolean result = isMatch2(s, pattern);
        System.out.println("result=" + result);
    }

    //动态规划
    private static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 2; i <= n; i++) {
            dp[0][i] = dp[0][i - 2] && p.charAt(i - 1) == '*';
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2]
                            || (dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'));
                }
            }
        }

        return dp[m][n];
    }

    //递归
    private static boolean isMatch2(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean first_match = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return (isMatch2(s, p.substring(2)) || (first_match && isMatch2(s.substring(1), p)));
        } else {
            return first_match && isMatch2(s.substring(1), p.substring(1));
        }
    }

    //有限状态自动机



}
