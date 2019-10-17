/**
 * leetcode 6 Zigzag 这题没什么好说的，用求余也可以，第一行最后一行去掉，中间的规律也极其简单。
 * 主要是，求余明显消耗海量的操作时间，能不用除法就不要用除法，这也和最近在看的乘除的原理有关系。
 */

public class Solution {

    public static void main(String[] args) {
        int num = 4;
        String str = "LEETCODESHIRING";
        String result = testZigZagConvert3(str, num);
        System.out.println("result=" + result);
    }

    private static String testZigZagConvert3(String str, int numRows) {

        StringBuffer sb = new StringBuffer();
        int divisor = 2 * (numRows - 1);
        if (numRows <= 1) {
            return str;
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; i+j < str.length(); j += divisor) {
                sb.append(str.charAt(i + j));
                if (i != 0 && i != numRows - 1 && divisor + j - i < str.length()) {
                    sb.append(str.charAt(divisor + j - i));
                }
            }
        }

        return sb.toString();
    }

    private static String testZigZagConvert2(String str, int numRows) {
        StringBuffer sb = new StringBuffer();
        int divisor = 2 * (numRows - 1);
        if (numRows <= 1) {
            return str;
        }
        for (int i = 1; i <= numRows; i++) {
            for (int j = 1; j <= str.length(); j++) {
                int remainder1 = i;
                int remainder2 = (2 * numRows - i) % divisor;
                if (j % divisor == remainder1 || j % divisor == remainder2) {
                    sb.append(str.charAt(j - 1));
                }
            }
        }
        return sb.toString();
    }

    private static String testZigZagConvert1(String str, int numRows) {
        StringBuffer sb = new StringBuffer();
        int mod = 2 * numRows - 2;
        if (mod <= 0) {
            return str;
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < str.length(); j++) {
                if ((j + 1) % mod == (((2 * numRows - i - 1) % (mod + 2)) % mod) || ((j + 1) % mod == ((2 * numRows + i + 1) % (mod + 2)) % mod)) {
                    sb.append(str.charAt(j));
                }
            }
        }
        return sb.toString();
    }
}
