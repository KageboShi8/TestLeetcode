import java.util.HashMap;

/**
 * leetcode 873 最长fib子序列长度
 * 剩下了动态规划没做
 */

public class Solution {
    public static void main(String[] args) {
        int[] input = {2, 4, 7, 8, 9, 10, 14, 15, 18, 23, 32, 50};
        int res = lenLongestFibSubseq(input);
        System.out.println(res);
    }

    private static int lenLongestFibSubseq(int[] nums) {
        int res = 0;
        int len = nums.length;
        int[] dp = new int[len];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int maxInSub = 0;
                int m = nums[i];
                int n = nums[j];
                while (map.containsKey(m + n)) {
                    int p = map.get(m + n);
                    m = n;
                    n = nums[p];
                    maxInSub++;
                }
                if (maxInSub != 0) {
                    dp[i] = Math.max(maxInSub + 2, dp[i]);
                }
            }
            res = Math.max(dp[i], res);
        }

        return res;
    }

    public static int findPosition(int num, int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                return i;
            }
        }

        return -1;
    }
}
