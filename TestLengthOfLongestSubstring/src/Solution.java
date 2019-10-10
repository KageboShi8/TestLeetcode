import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * leetcode 3
 * 这题已经不太会做了 题目是希望找到最长不重复字符串，方法1使用暴力搜索，不过这里的暴力搜索复杂度一定是个O(n^3)，n^2遍历所有字符串，再来一次n进行唯一性比较。不过这种做法必然超时，极不可取。
 * 方法2 使用滑动窗口，是一个非常常用的减少复杂度的方法，通过双指针的滑动，动态调整测量的字符串，需要好好把握，唯一性比较的部分留给HashSet，这也是一个降低复杂度的常用手段
 * 方法3 是滑动窗口的优化，看似是一个map对hashset的优化，其实，完全看不懂。和上题其实是一个set->map的思路，这里却理解的不够好。
 * 另外，这题有很严重的边界行问题，在输入字符串为空时要自己考虑。
 */

public class Solution {
    public static void main(String[] args) {
        String testString = "abcabcbb";
        int result = lengthOfLongestSubstring2(testString);
        System.out.println("result=" + result);
    }


    //用map优化滑动窗口
    private static int lengthOfLongestSubstring2(String testString) {
        int ans = 0;
        int length = testString.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < length; j++) {
            if (map.containsKey(testString.charAt(j))) {
                i = Math.max(map.get(testString.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(testString.charAt(j), j + 1);
//            showMap(map);
        }

        return ans;
    }

    private static void showMap(Map<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry :
                map.entrySet()) {
            System.out.print("key=" + entry.getKey() + " value=" + entry.getValue());
        }
    }


    // 滑动窗口
    private static int lengthOfLongestSubstring1(String testString) {
        int length = testString.length();
        int i = 0;
        int j = 0;
        int ans = 0;
        HashSet<Character> set = new HashSet<>();
        while (i < length && j < length) {
            if (!set.contains(testString.charAt(j))) {
                set.add(testString.charAt(j));
                j++;
                ans = Math.max(ans, j - i);
            } else {
                set.remove(testString.charAt(i));
                i++;
            }
        }
        return ans;
    }

    //暴力搜索
    private static int lengthOfLongestSubstring0(String testString) {
        int n = testString.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(testString, i, j)) {
                    ans = Math.max(ans, j - i);
                }
            }
        }
        return ans;
    }

    private static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }
}
