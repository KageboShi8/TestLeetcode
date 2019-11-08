/**
 * leetcode 14 最长公共前缀
 * 关于这个最长前缀问题，看着很简单，其实当然也很简单。不过，其中既包含了字符串的知识，另外竟还是二分法和分治法的启蒙，作为一个启蒙题简直不要太好哦
 *
 */


public class Solution {
    public static void main(String[] args) {
        String[] str = new String[]{"flower", "flow", "fl"};
        String result = longestCommonPrefix4(str);
        System.out.println("result=" + result);
    }

    //二分法  无限的拆字符串，左边比对，对上就拆右边，若没对上，就接着拆左边
    private static String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            minLen = Math.min(minLen, strs[i].length());
        }
        int low = 1;
        int high = minLen;
        while (low < high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle)) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        return strs[0].substring(0, (low + high) / 2);
    }

    private static boolean isCommonPrefix(String[] strs, int len) {
        String str1 = strs[0].substring(0, len);
        for (int i = 0; i < strs.length; i++) {
            if (!strs[i].startsWith(str1)) {
                return false;
            }
        }
        return true;
    }


    //极其著名的分治法 方法也非常清晰，将每个地方一分为二，最终相等就结束，要不然，一边剩一个，再比最后一次就可以。
    private static String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private static String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        } else {
            int mid = (l + r) / 2;
            String lcpLeft = longestCommonPrefix(strs, l, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, r);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    private static String commonPrefix(String lcpLeft, String lcpRight) {
        int min = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < min; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, min);
    }


    //水平扫描法 设定一个result result和每个都比  关键在于while循环中indexof的使用，极大的减小了复杂度
    private static String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String result = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(result) != 0) {
                result = result.substring(0, result.length() - 1);
                if (result.isEmpty()) return "";
            }
        return result;
    }

    //暴力搜索法 双重循环  单字比较，不同就跳出
    private static String longestCommonPrefix1(String[] str) {
        boolean flag = true;
        StringBuilder result = new StringBuilder();
        if (str.length == 0) {
            return "";
        }
        for (int k = 0; k < str[0].length(); k++) {
            char c = str[0].charAt(k);
            for (int l = 0; l < str.length; l++) {
                if (k < str[l].length() && str[l].charAt(k) == c) {

                } else {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                break;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

}
