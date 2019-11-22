/**
 * leetcode 28 实现strStr
 * 所谓的strStr就是匹配子字符串是否存在于母字符串中；
 * 如果可以用substring实在是太简单了 ，这里使用kmp算法解决比较科学
 * 字符串匹配，最终都是模式匹配的问题，也就是说，都是有限状态自动机的构建。关于字符串有很多经典的算法，这个以后总归要一起看一下的。
 * 这里简单介绍一下kmp算法。 暴力搜索自然简单，一个个比过来就好了，指针每次都回退就行。kmp就是让指针不要直接回退到i+1，而是回退到一个
 * 科学的地方。毕竟前面那些都比较过，再比较也没意义，所以问题在于pmt的构建，pmt就是所谓的科学的地方的数组。
 * pmt叫做所谓的部分匹配表，当然还有很多变种，不过，根本原理其实是一样的，也就是一串字符的前后缀的重复个数所生成的表。而之所以其是科学的
 * 指针回退位置其实是因为所谓的pattern和text最后是串的后缀和前缀的比较，既然已经有了匹配个数和长度，那么回退到那里必然是科学的。当然，还有
 * 很多的细节问题需要考虑就是了。
 */

public class Solution {
    public static void main(String[] args) {
        String hayStack = "hello";
        String needle = "ll";
        int result = strStrKMP(hayStack, needle);
        System.out.println(result);
    }

    private static int strStr1(String hayStack, String needle) {
        if (needle.isEmpty()) return 0;
        if (hayStack.isEmpty()) return -1;
        int length = needle.length();
        for (int i = 0; i < hayStack.length() - length + 1; i++) {
            if (hayStack.substring(i, i + length).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    private static int strStr2(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        if (haystack.isEmpty()) return -1;
        return searchStr(haystack, needle);
    }


    //暴力搜索 O(m*n)
    private static int searchStr(String haystack, String needle) {
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            int j;
            for (j = 0; j < needle.length(); j++) {
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    break;
                }
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }

    private static int strStrKMP(String haystack, String needle) {
        int[] maxMatchLengths = caculateMaxMatchLength(needle);
        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && needle.charAt(j) != haystack.charAt(i)) {
                j = maxMatchLengths[j - 1];
            }
            if (needle.charAt(j) == haystack.charAt(i)) {
                j++;
            }
            if (j == needle.length()) {
                return i - needle.length() + 1;
            }
        }
        return -1;
    }

    //辅助数组PMT(部分匹配表)的建立，就是他的值决定了这个算法的优越性
     private static int[] caculateMaxMatchLength(String pattern) {
        int[] maxMatchLength = new int[pattern.length()];
        int maxLength = 0;
        for (int i = 0; i < pattern.length(); i++) {
            while (maxLength > 0 && pattern.charAt(i) != pattern.charAt(maxLength)) {
                maxLength = maxMatchLength[i];
            }
            if (pattern.charAt(i) == pattern.charAt(maxLength)) {
                maxLength++;
            }
            maxMatchLength[i] = maxLength;
        }

        return maxMatchLength;
    }


}
