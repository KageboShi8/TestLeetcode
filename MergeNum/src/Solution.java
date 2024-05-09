import java.util.Arrays;

//leetcode 88  合并有序数组
public class Solution {
    public static void main(String[] args) {
        int[] ints1 = {1, 2, 3, 0, 0, 0};
        int[] ints2 = {2, 5, 6};

      merge1(ints1, 3, ints2, 3);
//        System.out.println(Arrays.toString(mergeInt));
    }

    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m + n];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                result[index] = nums1[i];
                i++;
                index++;
            } else if (nums1[i] > nums2[j]) {
                result[index] = nums2[j];
                j++;
                index++;
            }
        }
        while (j < n || i < m) {
            if (j < n) {
                result[index] = nums2[j];
                index++;
                j++;
            } else if (i < m) {
                result[index] = nums1[i];
                index++;
                i++;
            }
        }
//        for (int x = 0; x != m + n; ++x) {
//            nums1[x] = result[x];
//        }
        return result;
    }

    //逆向双指针 针对要在原数组更改的情况 不用额外的空间
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int index = m + n - 1;
        while (i > 0 && j > 0) {
            if (nums1[i] <= nums2[j]) {
                nums1[index] = nums2[j];
                j--;
                index--;
            } else if (nums1[i] > nums2[j]) {
                nums1[index] = nums1[i];
                i--;
                index--;
            }
        }
        while (j > 0 || i > 0) {
            if (j > 0) {
                nums1[index] = nums2[j];
                index--;
                j--;
            } else if (i > 0) {
                nums1[index] = nums1[i];
                index--;
                i--;
            }
        }
        System.out.println(Arrays.toString(nums1));

    }


}
