import java.util.Arrays;

/**
 * leetcode 493  翻转对问题
 */

public class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
        int res = reversePairs1(nums);
        System.out.println(res);
    }

    //如果不考虑归并分治，其实暴力求解也能做，说来说去也就是n2的复杂度
    private static int reversePairs(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((long) (nums[i]) > (long) (2 * nums[j])) {
                    res++;
                }
            }
        }
        return res;
    }


    //考虑归并分治
    private static int reversePairs1(int[] nums) {
        int arr[] = new int[nums.length];
        return reverseP(nums, arr, 0, nums.length - 1);

    }

    private static int reverseP(int[] nums, int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = (l + r) / 2;
        return reverseP(nums, arr, l, mid) + reverseP(nums, arr, mid + 1, r) + reverseCross(nums, arr, l, mid, r);
    }

    private static int reverseCross(int[] nums, int[] arr, int l, int mid, int r) {

        //统计部分
        int ans = 0;
        for (int i = l, j = mid + 1; i <= mid; i++) {
            while ((j <= r) && ((long) nums[i] > (long) nums[j] * 2)) {
                j++;
            }
            ans += j - mid - 1;
        }

        //归并排序部分
        int t = l;
        int a = l;
        int b = mid + 1;

        while (a <= mid && b <= r) {
            arr[t++] = (nums[a] <= nums[b]) ? nums[a++] : nums[b++];
        }

        while (a <= mid) {
            arr[t++] = nums[a++];
        }

        while (b <= r) {
            arr[t++] = nums[b++];
        }
        for (int i = l; i <= r; i++) {
            nums[i] = arr[i];
        }

        return ans;
    }
}