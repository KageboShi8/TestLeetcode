import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 18  四数之和
 * 这题和三数和也是一样的，双指针，没什么搞头，主要是编码能力，有一点小坑，不太容易bug free
 */

public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{-3, -2, -1, 0, 0, 1, 2, 3};
        int target = 0;
        List<List<Integer>> result = fourNum(nums, target);
        listOutput(result);
    }

    private static void listOutput(List<List<Integer>> result) {
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j) + " ");
            }
            System.out.println("");
        }
    }

    private static List<List<Integer>> fourNum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) return new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int l = j + 1;
                int r = nums.length - 1;
                while (l < r) {
                    int i1 = nums[i] + nums[j] + nums[l] + nums[r];
                    if (i1 > target) {
                        while (l < r && nums[r] == nums[--r]) ;
                    } else if (i1 < target) {
                        while (l < r && nums[l] == nums[++l]) ;
                    } else if (i1 == target) {
                        boolean contains = result.contains(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[l], nums[r])));
                        if (!contains) {
                            result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[l], nums[r])));
                        }
                        while (l < r && nums[r] == nums[--r]) ;
                        while (l < r && nums[l] == nums[++l]) ;
                    }
                }
            }
        }


        return result;
    }

}
