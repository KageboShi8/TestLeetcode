import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 118  杨辉三角
 */

public class Solution {

    public static void main(String[] args) {
        List<List<Integer>> res = generate(5);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(Arrays.toString(res.get(i).toArray()));
        }
    }

    private static List<List<Integer>> generate(int numRows) {
        int[][] dp = new int[numRows][numRows];
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            dp[i][0] = 1;
            ArrayList<Integer> sublist = new ArrayList<>();
            sublist.add(dp[i][0]);
            for (int j = 1; j <= i; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                sublist.add(dp[i][j]);
            }
            list.add(sublist);
        }

        return list;
    }
}
