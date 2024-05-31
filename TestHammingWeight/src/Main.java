/**
 * leetcode 191 位1的个数
 */

public class Main {
    public static void main(String[] args) {
        int n = 3;
        int res = hammingWeight(n);
        System.out.println(res);
    }

    private static int hammingWeight(int n) {
        if (n == 1) return 1;
        int count = 0;
        while (n >= 2) {
            n = n & (n - 1);
            count++;
        }


        return count;
    }
}