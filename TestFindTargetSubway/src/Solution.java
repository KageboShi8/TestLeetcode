/*
    leetcode 494 目标和 在数组中加上加号或者减号，得到目标结果
    这里用的是深度优先的回溯，当然也可以使用动态规划
 */

public class Solution {

    public static void main(String[] args) {
        int[] input = new int[]{100};
//        int res = findTargetSubWay(input, 3);
        int res1 = findTargetSubWay2(input, -200);
        System.out.println(res1);
    }

    private static int findTargetSubWay2(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if ((sum + target) % 2 == 1 || sum < target||(sum+target)<0) return 0;
        int tar = (sum + target) / 2;
        int dp[] = new int[tar + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = tar; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[tar];
    }

    private static int findTargetSubWay(int[] nums, int target) {
        int n = nums.length;
        int index = 0;
        int curSum = 0;
        int count = dfs(index, curSum, n, target, nums);
        return count;
    }

    private static int dfs(int i, int curSum, int size, int target, int[] nums) {

        if (i == size) {
            if (curSum == target) {
                return 1;
            } else {
                return 0;
            }
        }
        int ans = dfs(i++, curSum - nums[i], nums.length, target, nums)
                + dfs(i++, curSum + nums[i], nums.length, target, nums);
        return ans;
    }


}
