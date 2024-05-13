/**
 * leetcode 45 跳跃游戏II
 */

public class Solution {
    public static void main(String[] args) {

        int[] nums = new int[]{2, 3, 1, 1, 4};
        int res = jump(nums);
        System.out.println(res);
    }

    //普通方法
    private static int jump(int[] nums) {
        int n = nums.length;
        int step = 0;
        int maxPosition = n - 1;
        int max = n - 1;
        if (n == 1) {
            return 1;
        }
        while (max > 0) {
            for (int i = max - 1; i >= 0; i--) {
                if (nums[i] + i >= max) {
                    maxPosition = Math.min(maxPosition, i);
                }
            }
            max = maxPosition;
            step++;
        }
        return step;
    }

    //贪心算法
    private static int jump1(int[] nums) {
        int max_far=0;
        int step=0;
        int end=0;
        for (int i = 0; i < nums.length; i++) {
            max_far=Math.max(nums[i]+i,max_far);
            if (i==end){
                end=max_far;
                step++;
            }

        }
        return step;

    }
}
