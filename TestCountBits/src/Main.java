import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 338 比特位计数
 */

public class Main {
    public static void main(String[] args) {

        int n = 5;
        int[] res = countBits(n);
        System.out.println(Arrays.toString(res));
    }

    private static int[] countBits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        if (n==0) return dp;
        dp[1] = 1;
        int index = 2;
        while (index <= n) {
            int i = 1;
            while ((2 << i) <= index) {
                i++;
            }
            dp[index] = 1 + dp[index - (2 << (i - 1))];
            index++;
        }
        return dp;
    }

    //注意这个i&i-1其实就和上面的index-2<<(i-1)是一个意思
    public int[] countBits1(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }

}


