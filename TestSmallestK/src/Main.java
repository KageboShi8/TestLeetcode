import java.util.Arrays;

/**
 * 面试题17.14 最小k个数
 */

public class Main {
    static int[] res;
    static int left;
    static int right;

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 2, 4, 6, 8};
        int k = 4;
        res = new int[k];
        smallestK(nums, k);
        System.out.println(Arrays.toString(res));
    }

    //完全的快速选择的思路，找到第k个数，然后将前面的打印出来就好了,shenzhibuxua
    private static int[] smallestK(int[] arr, int k) {

        quickChoose(arr, 0, arr.length - 1, k);
        return res;
    }

    private static void quickChoose(int[] arr, int l, int r, int k) {
        if (l > r) {
            return;
        }
        int x = arr[l + (int) (Math.random() * (r - l + 1))];
        partition(arr, l, x, r);
        int lPos = left;
        int rPos = right;
        if (k < lPos) {
            quickChoose(arr, l, lPos, k);
        } else if (k > rPos) {
            quickChoose(arr, rPos, r, k);
        } else {
            generateArray(arr, k, lPos);
        }

    }

    private static void partition(int[] arr, int l, int x, int r) {
        int a = l;
        int b = r;
        int i = l;
        while (i <= b) {
            if (arr[i] > x) {
                swapNum(arr, i, b);
                b--;
            } else if (arr[i] < x) {
                swapNum(arr, i, a);
                i++;
                a++;
            } else {
                i++;
            }
        }
        left=a;
        right=b;
    }

    private static void swapNum(int[] arr, int index1, int index2) {
        if (index2 == index1) return;
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    private static void generateArray(int[] arr, int k, int lPos) {
        for (int i = 0; i < k; i++) {
            if (i >= lPos) {
                res[i] = arr[lPos];
            } else {
                res[i] = arr[i];
            }
        }
    }
}