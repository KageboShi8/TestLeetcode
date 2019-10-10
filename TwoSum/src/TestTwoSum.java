import java.util.HashMap;
import java.util.Map;


/*
leetcode 1 Two Sum
方法0，方法1，方法2 在此数据情况下 分别耗时23ms，4ms，3ms
方法0使用的是暴力搜寻，复杂度O(n^2)，方法1使用了map，先插入map，再通过map的key的唯一性进行搜索，毕竟map的查询只需要O(1)，通过这个完成了复杂度的简化，
两次O(n),完成了复杂度的简化，方法2只不过是方法1的精简版，省去了先插再找的两次O(n)遍历，直接在插入时，一次解决，一次O(n)，所以略有加速；
 */
public class TestTwoSum {
    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] index = twoSum2(nums, target);
        System.out.println(index[0] + "," + index[1]);
    }

    private static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.size() != 0) {
                if (map.containsKey(target - nums[i])) {
                    if (map.get(target - nums[i]) != i) {
                        return new int[]{map.get(target - nums[i]), i};
                    }
                }
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    private static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                if (map.get(target - nums[i]) != i) {
                    return new int[]{i, map.get(target - nums[i])};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private static int[] twoSum0(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
