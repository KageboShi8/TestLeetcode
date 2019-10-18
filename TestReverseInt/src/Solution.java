import sun.rmi.server.InactiveGroupException;

/**
 * leetcode 7 reverse integer
 * 本题其实非常简单，反转int本来就不太消耗时间复杂度，只和int的长度有关，本题的无法AC的原因主要是整形的溢出问题
 * 正数到0x7fffffff 负数到-0x80000000 所以加两条判断即可
 */

public class Solution {
    public static void main(String[] args) {
        int x = 1534236469;
        int result = reverseInt(x);
        System.out.println("result=" + result);
    }

    private static int reverseInt(int x) {
        int result = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            result = result * 10 + pop;
        }
        return result;
    }
}
