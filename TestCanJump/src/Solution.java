/**
 * leetcode 55 跳跃游戏
 */
public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 0};
        boolean res = canJump(nums);
        System.out.println(res);
    }

    //这个和跳跃游戏II有一点类似，不过比一简单，用II的方法肯定能行，不过也可以考虑一维的动态规划

    //正常做法，重点是能不能穿过其中的0；
    private static boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        if (nums[0] == 0) return false;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                if (!checkCross(i, nums)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkCross(int i, int[] nums) {
        for (int j = i - 1; j >= 0; j--) {
            if (j + nums[j] > i) {
                return true;
            }
        }
        return false;
    }

    //动态规划
    private static boolean canJump1(int[] nums) {
        if (nums.length == 1) return true;
        int[] dp = new int[nums.length + 1];
        if (nums[0] == 0) return false;
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + i);
            if (dp[i] >= nums.length - 1) {
                return true;
            }
            if (dp[i] == i) {
                return false;
            }
        }
        return true;
    }

    //贪心算法
    private static boolean canJump2(int[] nums) {
        int max_far = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < max_far) {
                max_far = Math.max(max_far, nums[i] + i);
                if (max_far >= nums.length - 1) {
                    return true;
                }
            }

        }
        return false;
    }

}
