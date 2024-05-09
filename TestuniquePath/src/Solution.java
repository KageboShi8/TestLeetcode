/*
 leetcode 62 不同路径问题
 */

public class Solution {
    public static void main(String[] args) {
        int res=uniquePaths(3,7);
        System.out.println(res);
    }

    private static int uniquePaths(int m, int n) {
        if (m==0||n==0) return 0;
        int[][] dp=new int[n+1][m+1];
        for(int i=0;i<n;i++){
            dp[i][0]=1;
        }
        for(int i=0;i<m;i++){
            dp[0][i]=1;
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
                System.out.println("dp["+i+"]["+j+"]="+dp[i][j]);
            }
        }
        return dp[n-1][m-1];
    }
}
