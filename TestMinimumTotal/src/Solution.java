import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 120 三角形最小路径和
 */
public class Solution {

    public static void main(String[] args) {
        List<List<Integer>> list = generateTriangle();
        int res = minimumTotal1(list);
        System.out.println(res);
    }

    //正常思路
    private static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int m = triangle.get(triangle.size() - 1).size();
        int[][] dp = new int[n][m];
        dp[0][0] = triangle.get(0).get(0);
        if (n == 1) return dp[0][0];
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0)
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                else if (j == i)
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                else
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
                if (i == n - 1) {
                    res = Math.min(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    //逆序思路
    private static int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        int m = triangle.get(triangle.size() - 1).size();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= triangle.get(i).size() - 1; j++) {
                dp[i][j] = Math.min(dp[i + 1][j + 1], dp[i + 1][j]) + triangle.get(i).get(j);

                System.out.println("dp" + i + j + "=" + dp[i][j]);
            }
        }
        return dp[0][0];
    }

    private static List<List<Integer>> generateTriangle() {
        List<List<Integer>> list = new ArrayList<>();

        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list.add(list1);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list.add(list2);

        List<Integer> list3 = new ArrayList<>();
        list3.add(6);
        list3.add(5);
        list3.add(7);
        list.add(list3);

        List<Integer> list4 = new ArrayList<>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        list.add(list4);

        return list;

    }
}
