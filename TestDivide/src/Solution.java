/**
 * leetcode 29 两数相除
 * 思路很简单，问题是整数的界的问题。
 * 界处理也不算难，主要要注意负数是比正数的域要大1的，所以，abs不能滥用。有一个很投机的写法，就是全化作负数处理，这样就比较好办。
 * 在界处理之后，会发现有超时问题，超时问题是由循环次数过多带来的。不可能每次都减divisor达到结果的，毕竟如果divisor过小，那循环次数
 * 会大到不可想象。所以，在循环中一般来说要动态调整divisor的大小。通过移位来倍增divisor的大小。直到达到一个合适的值在进行商的调整。
 * 当然divisor的每次移位自然也伴随着商的移位，这点要提请注意。
 */

public class Solution {
    public static void main(String[] args) {
        int dividend = -5;
        int divisor = -1;
        int result = divide1_1(dividend, divisor);
        System.out.println(result);
    }

    //这方法极快 一方面是变负数，另一方面来自其每次的移位（乘除2带来的好处）
    private static int divide2(int dividend, int divisor) {
        boolean sign = (dividend > 0) ^ (divisor > 0);
        int result = 0;
        if (dividend > 0) dividend = -dividend;
        if (divisor > 0) divisor = -divisor;
        while (dividend <= divisor) {
            int temp_result = -1;
            int temp_divisor = divisor;
            while (dividend <= (temp_divisor << 1)) {
                if (temp_divisor <= (Integer.MIN_VALUE >> 1)) break;
                temp_result = temp_result << 1;
                temp_divisor = temp_divisor << 1;
            }
            dividend -= temp_divisor;
            result += temp_result;
        }
        if (!sign) {
            if (result <= Integer.MIN_VALUE) return Integer.MAX_VALUE;
            result = -result;
        }
        return result;
    }

    //此方法是对的，切成负数比较令人愉快，毕竟负数的域比整数的大1；问题是会超时
    private static int divide1(int dividend, int divisor) {
        boolean sign = (dividend > 0) ^ (divisor > 0);
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int result = 0;
        int newDividend = (dividend > 0) ? -dividend : dividend;
        int newDivisor = (divisor > 0) ? -divisor : divisor;
        while (newDividend <= newDivisor) {
            newDividend -= newDivisor;
            result++;
        }
        result = sign ? -result : result;
        return result;
    }

    //方法1改
    private static int divide1_1(int dividend, int divisor) {
        boolean sign = (dividend > 0) ^ (divisor > 0);
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int result = 0;
        dividend = (dividend > 0) ? -dividend : dividend;
        divisor = (divisor > 0) ? -divisor : divisor;
        while (dividend <= divisor) {
            int tempDivisor = divisor;
            int tempResult = 1;
            while (dividend < (tempDivisor << 1)) {
                if (tempDivisor < (Integer.MIN_VALUE >> 1)) break;
                tempDivisor = tempDivisor << 1;
                tempResult = tempResult << 1;
            }
            result += tempResult;
            dividend -= tempDivisor;
        }
        result = sign ? -result : result;
        return result;
    }

}
