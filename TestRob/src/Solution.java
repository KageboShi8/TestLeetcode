/**
 * 打家劫舍合集
 * leetcode 198 打家劫舍
 * leetcode 213 打家劫舍II
 * leetcode 337 打家劫舍III
 */

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        TreeNode root = initRoot();
//        int res = rob2_1(nums);
        int res = rob3(root);
        System.out.println(res);
    }

    private static int rob3(TreeNode root) {



        return 0;
    }

    private static TreeNode initRoot() {
        TreeNode root = new TreeNode(3);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        root.left = left1;
        root.right = right1;

        TreeNode left1_right = new TreeNode(3);
        TreeNode right1_right = new TreeNode(1);

        left1.right = left1_right;
        right1.right = right1_right;

        return root;
    }

    //打家劫舍II 思路就是使用0~n-1 和 1~n两个序列 得出其最大值
    private static int rob2(int[] nums) {
        //确认状态 0：0~n-2 1：1~n-1
        int n = nums.length;
        int[][] dp = new int[n][2];
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        dp[0][0] = nums[0];
        dp[1][0] = Math.max(nums[0], nums[1]);
        dp[0][1] = 0;
        dp[1][1] = nums[1];

        for (int i = 2; i < n - 1; i++) {
            dp[i][0] = Math.max(dp[i - 2][0] + nums[i], dp[i - 1][0]);
            System.out.println("dp" + i + "0=" + dp[i][0]);
        }
        for (int j = 2; j < n; j++) {
            dp[j][1] = Math.max(dp[j - 2][1] + nums[j], dp[j - 1][1]);
            System.out.println("dp" + j + "0=" + dp[j][1]);
        }


        return Math.max(dp[n - 2][0], dp[n - 1][1]);
    }

    //打家劫舍II 精简内存版
    private static int rob2_1(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        return Math.max(robRange(nums, 0, n - 1), robRange(nums, 1, n));

    }

    private static int robRange(int[] nums, int start, int end) {
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i < end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    // 打家劫舍I  要注意 n<=2 时的返回
    private static int rob1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        if (n == 1)
            return nums[0];
        dp[0] = nums[0];
        if (n == 2)
            return Math.max(nums[0], nums[1]);
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}