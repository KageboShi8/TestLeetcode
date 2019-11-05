/**
 * leetcode 9 palindrome
 * 这题是判断回文数，直接整数转字串，逐个比较字符是否相等自然是简单也不算花时间的办法， 参见法1
 * 不过，若然不转字符串的话，纯用数字解决也很简单，取最高位和最低位就可以了，重点是拿到数字的位数，见法2
 * 法3是极其精妙的写法 只取后半数字的反转，循环判断条件及其精妙，return的返回值也及其精妙。
 */

public class Solution {
    public static void main(String[] args) {
        int m = 12343211;
        boolean result = isPalindrome3(m);
        System.out.println("result=" + result);
    }

    //字符串解决法
    private static boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        }
        String s = String.valueOf(x);
        int len = s.length();
        if (len == 1) {
            return true;
        }
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    //数字解决法
    private static boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x > 0) {
            if (x % 10 != x / div) {
                return false;
            }
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    //数字折半法
    private static boolean isPalindrome3(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertNumber = 0;
        while (x > revertNumber) {
            revertNumber = revertNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertNumber || x == revertNumber / 10;
    }

}
