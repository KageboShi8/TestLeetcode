import java.lang.reflect.Array;
import java.util.*;

/**
 * leetcode 15 三数之和
 * 本题比我想象的要简单，简化也不过是双指针加上排序加上剪枝而已。非常常规的操作，并没有什么神仙解法，最多就是要关心这个双指针的
 * 使用，而且，这个单行while的使用很神奇，感觉有点独一无二的写法的味道，其实手撕比较关键，方法很好理解
 */


public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum2(nums);
        sysOut(result);

    }

    private static void sysOut(List<List<Integer>> result) {
        if (result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                List<Integer> integers = result.get(i);
                for (int j = 0; j < integers.size(); j++) {
                    System.out.print(integers.get(j) + " ");
                }
                System.out.println(" ");
            }
        } else {
            System.out.println("");
        }

    }


    //暴力搜索 显然超出时间限制
    private static List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[k] + nums[j] == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        result.add(list);
                    }
                }
            }
        }

        return result;
    }

    //双指针
    private static List threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int k = 0; k < nums.length - 2; k++) {
            if (nums[k] > 0) {
                break;
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum < 0) {
                    while (i < j && nums[i] == nums[++i]) ;
                } else if (sum > 0) {
                    while (i < j && nums[j] == nums[--j]);
                } else {
                    result.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[j], nums[k])));
                    while (i < j && nums[i] == nums[++i]) ;
                    while (i < j && nums[j] == nums[--j]) ;
                }

            }
        }
        return result;
    }

}
