/*
leetcode 1137 第n个太波那切数
T4=T3+T2+T1;
 */

import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {
        int res = tribonacci1(4);
        System.out.println(res);
    }

    private static int tribonacci(int n) {
        if (n == 1) return 1;
        if (n == 0) return 0;
        if (n == 2) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            System.out.println("dp="+dp[i]);
        }

        return dp[n];
    }
    //滚动数组法
    private static int tribonacci1(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;
        int p=0;
        int q=1;
        int r=1;
        int s=0;

        for (int i = 3; i <= n; i++) {
            s=p+q+r;
            p=q;
            q=r;
            r=s;
        }

        return s;
    }
}
