/**
 * leetcode 11 盛水最多的容器
 * 所谓的盛水最多的计算方法非常简单，大致就是最大面积。暴力搜索是一个n^2,问题就是时间复杂度的减少，双指针法是一个非常通用的思路。
 * 此题的双指针的简化，最重要的就只有一点，因为每次都是小值做面积的边，所以不可能动大边,动大边自然越动越小,问题是为何它最大.
 * 通俗来说，每次移动短板一定得到的是下一个位置的最有情况，不会丢失面积最大值（甚至不会导致状态的的丢失），所以内移短板一定是安全
 * 的，所以，最后比较的max一定是真实的max。
 */


public class Solution {
    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result;
        result = maxArea2(height);
        System.out.println("result=" + result);
    }

    //双指针法 说起来非常简单，想起来非常难的思路
    private static int maxArea2(int[] height) {
        int result = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
//            result = Math.max(result, Math.min(height[l], height[r]) * (r - l));
            result = Math.max((r - l) * Math.min(height[r], height[l]), result);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return result;
    }

    //暴力搜索
    private static int maxArea1(int[] height) {
        int result = 0;
        int m = height.length;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                result = Math.max((j - i) * Math.min(height[i], height[j]), result);
            }
        }
        return result;
    }

}
