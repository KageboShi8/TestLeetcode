import com.sun.security.auth.UnixNumericUserPrincipal;

import java.util.Arrays;

/**
 * leetcode 215 数组中第k个最大元素
 */

public class Main {
    static int[] nums = {3,2,1,5,6,4};

    static int[] resArr = new int[Main.nums.length];
    static int[] resArr1 = new int[nums.length];

    static int left;
    static int right;

    static int res;

    public static void main(String[] args) {
        int k = 2;
        int res = findKthLargest1(nums, k);
//        int res1 = findLargest(nums);
//        mergeSort2(nums);
        System.out.println(res);
    }

    //本题目考虑分治
    private static int findKthLargest(int[] nums, int k) {
        mergeSort(nums);
        return nums[k - 1];
    }

    //本题目考虑快速选择的通解， 运用于一切第k大的元素 是快速选择的思想
    //使用的荷兰国旗的方法，确定某数的精确位置，然后分割成前后中三份，再在其中一份中继续进行
    private static int findKthLargest1(int[] nums, int k) {
        findK(nums, 0, nums.length - 1, nums.length-k);
        return res;
    }

    private static void findK(int[] nums, int l, int r, int k) {
        int x = nums[l + (int) (Math.random() * (r - l + 1))];
        partitionK(nums, l, x, r);
        int lPos = left;
        int rPos = right;

        if (k < lPos) {
            findK(nums, l, lPos - 1, k);
        } else if (k > rPos) {
            findK(nums, rPos + 1, r, k);
        } else {
            res = nums[lPos];
        }
    }

    private static void partitionK(int[] nums, int l, int x, int r) {
        int a = l;
        int b = r;
        int i = l;
        while (i <= b) {
            if (nums[i] > x) {
                swapNum(nums, b, i);
                b--;
            } else if (nums[i] < x) {
                swapNum(nums, a, i);
                a++;
                i++;
            } else {
                i++;
            }
        }
        left = a;
        right = b;
    }

    //先写一个找到最大元素的分治，运用递归。
//    private static int findLargest(int[] nums) {
//        return findlarger(nums, 0, nums.length - 1);
//    }

//    private static int findlarger(int[] nums, int left, int right) {
//        if (left == right) {
//            return nums[left];
//        }
//        int mid = (right + left) / 2;
//        int lmax = findlarger(nums, left, mid);
//        int rmax = findlarger(nums, mid + 1, right);
//        return Math.max(lmax, rmax);
//    }

    //归并排序 递归版本
    private static void mergeSort(int[] nums) {
        mergeSortRecur(nums, 0, nums.length - 1);
    }

    private static void mergeSortRecur(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSortRecur(nums, left, mid);
        mergeSortRecur(nums, mid + 1, right);
        mergeArr(nums, left, mid, right);

    }

    //这里的lPos是左边的第一个，rPos是右边的第一个，这两个都是有序数组，现在要合并他两成为一个有序数组
    private static void mergeArr(int[] nums, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int t = left;
        while (i <= mid && j <= right) {
            resArr[t++] = (nums[i] <= nums[j]) ? nums[i++] : nums[j++];
        }
        while (i <= mid) {
            resArr[t++] = nums[i++];
        }
        while (j <= right) {
            resArr[t++] = nums[j++];
        }
        for (int k = left; k <= right; k++) {
            nums[k] = resArr[k];
        }
    }

    //归并排序 迭代版本
    private static void mergeSort2(int[] nums) {
        //l，m表示归并的左侧的头和尾，等同于递归版本的左侧,r表示右侧的最后
        for (int l, r, m, step = 1; step < nums.length; step *= 2) {
            l = 0;
            while (l < nums.length) {
                m = l + step - 1;
                if (m + 1 >= nums.length) {
                    //没有右侧
                    break;
                }
                r = Math.min(l + (step * 2) - 1, nums.length - 1);
                mergeArr1(nums, l, m, r);
                l = r + 1;
            }

        }
    }

    private static void mergeArr1(int[] nums, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int t = left;
        while (i <= mid && j <= right) {
            resArr1[t++] = (nums[i] <= nums[j]) ? nums[i++] : nums[j++];
        }
        while (i <= mid) {
            resArr1[t++] = nums[i++];
        }
        while (j <= right) {
            resArr1[t++] = nums[j++];
        }
        for (int k = left; k <= right; k++) {
            nums[k] = resArr1[k];
        }
    }


    //交换
    private static void swapNum(int[] nums, int indexA, int indexB) {
        if (indexA == indexB) return;
        int temp = nums[indexA];
        nums[indexA] = nums[indexB];
        nums[indexB] = temp;
    }

}