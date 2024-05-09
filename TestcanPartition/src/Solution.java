/**
 * leetcode 416 分割等和子集
 */

public class Solution {

    public static void main(String[] args) {
        int[] input = new int[]{9, 5};
        boolean res = canPartition(input);
        System.out.println(res);
    }

    //这里一维数组和二维数组的复杂度基本是一样的，不过空间节省了不少。而一维不过也就是个二维的小优化而已，思路都是一样的。
    //主要是要搞清楚转换方程
    private static boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1 || len < 2) return false;
        int target = sum / 2;
//        boolean[][] dp = new boolean[len][target + 1];
//        for (int i = 0; i < len; i++) {
//            dp[i][0] = true;
//        }
//        if (nums[0]<=target){
//            dp[0][nums[0]] = true;
//        }
//        for (int i = 1; i < len; i++) {
//            for (int j = 0; j < target + 1; j++) {
//                if (j > nums[i]) {
//                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]];
//                } else {
//                    dp[i][j] = dp[i - 1][j];
//                }
//            }
//        }


//        return dp[len - 1][target];

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < len; i++) {
            for (int j = target; j > nums[i]; j--) {
                dp[j] = dp[j] | dp[j - nums[i]];
            }
        }
        return dp[target];

    }


}
