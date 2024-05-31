/**
 * leetcode 53 最大子数组和
 */

public class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int max = maxSubArray1(nums);
        System.out.println(max);
    }

    // 要注意转移方程，这里前面是正的，就是加了更好，无论这个是正是负都没有办法，
    // 而前面是负的，不如推倒重建，而这个数如果是负的也没有办法
    private static int maxSubArray(int[] nums) {
        int max = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }

        return max;
    }

    //考虑分治 其实复杂度是O（nlogN） 是不如dp的
    private static int maxSubArray1(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int res = maxSubArraySum(nums, 0, nums.length - 1);
        return res;
    }

    private static int maxSubArraySum(int[] nums, int l, int r) {
        if (l == r) {
            return nums[l];
        }
        int mid = (l + r) / 2;
        return Math.max(Math.max(maxSubArraySum(nums, l, mid), maxSubArraySum(nums, mid + 1, r)),
                maxCrossingArraySum(nums, l, mid, r));

    }

    //这里是算跨过两者的最大和，也就是必然包含mid和mid+1位置的最大和，所以可以从中间向两边扩展
    private static int maxCrossingArraySum(int[] nums, int l, int mid, int r) {
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = mid; i >= l; i--) {
            sum += nums[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }


        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= r; i++) {
            sum += nums[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }


        return leftSum + rightSum;
    }
}