import java.util.Arrays;

/**
 * leetcode 27 移除元素
 * 这题也不难，主要是编码能力
 */

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
        int result = removeElement(nums, val);
        System.out.println("result=" + result);
    }

    private static int removeElement(int[] nums, int val) {
        int count = 0;
//        int i = 0;
        if (nums.length == 0) return 0;
//        while (i < nums.length) {
//            if (nums[i] != val) {
//                nums[count] = nums[i];
//                count++;
//                i++;
//            } else {
//                i++;
//            }
//        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[count] = nums[i];
                count++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return count;
    }

}
