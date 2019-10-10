import java.util.HashMap;


/**
 * Leetcode 4  Median of Two Sorted Arrays
 * 本题的关键在于复杂度的问题  要求时间复杂度O（log(m+n)）是本题的关键  如果没有时间复杂度的考量，那么直接合并两个数组再取中位就可以了，复杂度不过是遍历一遍数组O(m+n),所以在此处就没有列出此种方法
 * 此处的方法0，从思路上也非常好理解。中位数并不需要排序整个数组，获得长度的的一半位置的两个数字就行了，也即按序插入时到达m+n/2就停手，插入时使用hashmap作为容器，那么在取值时时间复杂度为O（1），所有的
 * 复杂度来自于一半的插入，这种方法，虽然略快于暴力插入，可是从时间复杂度来说，并没有什么本质变化，属于小改动，而非复杂度层面的提升。
 * 方法1和方法2，其实一本同源。这题有个很重要的思路在于二分法的使用。看到log（m+n），一定是二分法（二叉树），而此处的二分法其实来源于对于中位数的理解。
 * 中位数，从定义角度来看，就仅仅是位于有序数列中间的数（或二者平均）。其实，从另一个角度看，两个有序数组的中位数，其实就是要找到某个位置k，此k是正好分割了整个大数组的两端，使两端长度相等，左端最大小于右端最小。
 * 其实如果只是一个有序数组，就非常好理解了，就是位置的中间，两个有序数组，不过也是位置的中间，只是不太清楚出现在那个数组中，当然也要考虑左端最大和右端最小的问题。
 * 所以，先假设数组1长度小于数组2，在数组1中移动指针，数组2中的指针位置自然是(m+n+1)/2-1,暴力一点想，遍历完整个m，绝对可以获得这个数的位置，然后就是二分法的问题了，如果只是++1的遍历，最后的复杂度仍然是O(m+n),
 * 所以，就有了指针的跳动，二分法的跳动，每次跳动（M+n）/2个位置，最后进行微调，虽然这里跳动的细节不清楚，边界判断也不甚明朗，不过大致就是如此，最后得到了maxLeft和minRight就基本得出了结论。每次判断，由于两个指针
 * 是算出来的，所以只需要考虑指针越界就行了，指针所指的数的大小，要和另一个数组的前位相比较，然后再微调指针就可以了。当然，这样的实现可以通过递归，同样可以通过循环，（编码都不会）分辨对应方法1和方法2。
 * 本体无愧leetcode 第一个hard 有非常深刻的算法寓意在其中。仍需要细细揣摩。
 * 另外，本体还有归并排序的思路做法，虽然不满足时间复杂度要求，仍可以作为参考，本处并未给出，等待看完归并排序后再给出。
 */

public class Solution {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2, 4, 5, 6, 7, 8, 9, 10};
        double median = findMedianSortedArrays2(nums1, nums2);
        System.out.println("median=" + median);
    }


    //官方解法
    private static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            int temp = m;
            m = n;
            n = temp;
            int[] temps = nums1;
            nums1 = nums2;
            nums2 = temps;
        }
        int iMin = 0;
        int iMax = m;
        int halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMax + iMin) / 2;
            int j = halfLen - i;
            if (i < iMax && nums2[j - 1] > nums1[i]) {
                iMin = i + 1;
            } else if (i > iMin && nums1[i - 1] > nums2[j]) {
                iMax = i - 1;
            } else {
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }

                return (maxLeft + minRight) / 2.0;
            }

        }


        return -1;
    }


    //二分法递归
    private static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        //奇偶性的处理在这里，通过index的不同，调整了最后答案的位置。强无敌,奇数时两者一样，偶数时，后面一个得到的是后一位
        return 0.5 * (find(nums1, nums2, 0, 0, (length1 + length2 + 1) / 2) + find(nums1, nums2, 0, 0, (length1 + length2) / 2 + 1));
    }


    // TODO: 2019/9/30 这里的边界处理还是有问题 这个二分法递归真的要了我的命 仍未搞定
    private static double find(int[] nums1, int[] nums2, int start1, int start2, int count) {
        int i = count / 2;
        int len1 = start1 + count - 1;
        int len2 = start2 + count - 1;

        if (i > len1 || (start1 + i - 1) >= nums1.length) {
            return nums2[start2 + count - 1];
        }
        if (i > len2 || (start2 + i - 1) >= nums2.length) {
            return nums1[start1 + count - 1];
        }

        if (i == 0) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        if (nums1[Math.min(start1 + i - 1, nums1.length - 1)] < nums2[Math.min(start2 + i - 1, nums2.length - 1)]) {
            return find(nums1, nums2, start1 + Math.min(i, nums1.length), start2, count - Math.min(i, nums1.length));
        } else {
            return find(nums1, nums2, start1, start2 + Math.min(i, nums2.length), count - Math.min(i, nums2.length));
        }


    }

    private static double find0(int[] nums1, int[] nums2, int start1, int start2, int cnt) {
        int len1 = nums1.length - start1;
        int len2 = nums2.length - start2;
        if (len1 > len2) {
            return find0(nums2, nums1, start2, start1, cnt);
        }
        if (len1 == 0) {
            return nums2[start2 + cnt - 1];
        }
        if (cnt == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        if (nums1[start1 + Math.min(cnt / 2, len1) - 1] > nums2[start2 + cnt / 2 - 1]) {
            return find0(nums1, nums2, start1, start2 + cnt / 2, cnt - cnt / 2);
        } else {
            return find0(nums1, nums2, start1 + Math.min(cnt / 2, len1), start2, cnt - Math.min(cnt / 2, len1));
        }
    }


    //map暴力插入
    public static double findMedianSortedArrays0(int[] nums1, int[] nums2) {
        double ans = 0;
        int length = (nums1.length + nums2.length) / 2;
        int array = (nums1.length + nums2.length) % 2;
        int i = 0;
        int j = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        if (nums1.length == 0) {
            if (array == 1) {
                return nums2[length];
            } else {
                return ((double) ((((double) nums2[length]) + ((double) nums2[length - 1])) / 2));
            }
        }
        if (nums2.length == 0) {
            if (array == 1) {
                return nums1[length];
            } else {
                return ((double) ((((double) nums1[length]) + ((double) nums1[length - 1])) / 2));
            }
        }
        int temp = 0;
        for (int k = 0; k <= length; k++) {
            temp = k;
            if (i >= nums1.length) {
                map.put(k, nums2[j]);
                j++;
                continue;
            }
            if (j >= nums2.length) {
                map.put(k, nums1[i]);
                i++;
                continue;
            }
            if (nums1[i] < nums2[j]) {
                map.put(k, nums1[i]);
                i++;
            } else {
                map.put(k, nums2[j]);
                j++;
            }
        }

        if (array == 1) {
            ans = map.get(temp);
        } else {
            ans = ((double) ((((double) map.get(temp)) + ((double) map.get(temp - 1))) / 2));
        }
        return ans;
    }


}
