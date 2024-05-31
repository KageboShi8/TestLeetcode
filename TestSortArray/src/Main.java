import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * leetcode 912 排序数组  这里做一点排序的内容
 */

public class Main {
    static int[] temp;
    static int left;
    static int right;

    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, 2, 3, 5, 7, 6};
        temp = new int[nums.length];
        int[] res = sortArray(nums);
        System.out.println(Arrays.toString(res));
    }

    private static int[] sortArray(int[] nums) {
        //归并排序
//        mergeSort(nums, 0, nums.length - 1);
        //随机快排
//        quickSort(nums, 0, nums.length - 1);
        //荷兰国旗优化随机快排
//        quickSort1(nums, 0, nums.length-1);
        //堆排序
        heapSortMin(nums);
        return nums;
    }

    //默认大顶堆 排序升序
    private static void heapSort(int[] nums) {
        int n = nums.length;
        //堆生成 分为insertHeap和heapify 一个是向上调整 一个是向下调整
        //这里的大顶堆插入是自顶向下，如果是自底向上效果会更好，复杂度可以低到O(n)
//        for (int i = 0; i < nums.length; i++) {
//            insertHeap(nums, i);
//        }
        //自底向上建堆
        for (int i = n - 1; i >= 0; i--) {
            heapify(nums, i, n);
        }
        //调整数字将最大放到末尾
        while (n > 0) {
            swapNum(nums, 0, n - 1);
            heapify(nums, 0, n - 1);
            n--;
        }
    }

    //考虑小顶堆 排序降序 如果是小顶堆 升序的话就很难做，因为即便固定了最小，其余部分也很难保证顺序的协调性，只能做完了再倒过来
    private static void heapSortMin(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            insertHeapMin(nums, i);
        }
        while (n > 0) {
            swapNum(nums, 0, n - 1);
            heapifyMin(nums, 0, n - 1);
            n--;
        }
    }


    // (i-1)/2就是他的父节点序号 这只是一个向上调整大顶堆的方法
    // 这里的大顶堆插入是自顶向下，如果是自底向上效果会更好，复杂度可以低到O(n)
    private static void insertHeap(int[] nums, int i) {
        while (nums[(i - 1) / 2] < nums[i]) {
            swapNum(nums, (i - 1) / 2, i);
            i = (i - 1) / 2;
        }
    }

    private static void insertHeapMin(int[] nums, int i) {
        while (nums[(i - 1) / 2] > nums[i]) {
            swapNum(nums, (i - 1) / 2, i);
            i = (i - 1) / 2;
        }
    }

    //(2*i)+1和(2*i)+2是其子节点序号 一个向下调整大顶堆的方法 或者说是重新堆化的方法
    private static void heapify(int[] nums, int i, int size) {
        int l = (2 * i) + 1;
        while (l < size) {
//            if (l + 1 > size - 1) {
//                if (nums[l] > nums[i]) {
//                    swapNum(nums, l, i);
//                }
//                l = 2 * l + 1;
//            } else {
//                int indexBig = nums[l + 1] > nums[l] ? l + 1 : l;
//                if (nums[indexBig] > nums[i]) {
//                    swapNum(nums, indexBig, i);
//                }
//                i=indexBig;
//                l = 2 * indexBig + 1;
//            }
            int indexBig = (l + 1 < size && nums[l + 1] > nums[l]) ? l + 1 : l;
            if (nums[indexBig] > nums[i]) {
                swapNum(nums, indexBig, i);
            }
            i = indexBig;
            l = 2 * i + 1;


        }
    }

    private static void heapifyMin(int[] nums, int i, int size) {
        int l = i * 2 + 1;
        while (l < size) {
            int indexSmall = (l + 1 < size) && (nums[l + 1] < nums[l]) ? l + 1 : l;
            if (nums[indexSmall] < nums[i]) {
                swapNum(nums, indexSmall, i);
            }
            i = indexSmall;
            l = indexSmall * 2 + 1;
        }
    }

    private static void quickSort1(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int x = nums[l + (int) (Math.random() * (r - l + 1))];
        partition1(nums, l, x, r);
        int lPos = left;
        int rPos = right;
        quickSort1(nums, l, lPos - 1);
        quickSort1(nums, rPos + 1, r);
    }

    private static void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int x = nums[l + (int) (Math.random() * (r - l + 1))];
        int part = partition(nums, l, x, r);
        quickSort(nums, l, part - 1);
        quickSort(nums, part + 1, r);
    }

    //这个方法其实就是将大于x的放在一边，小于等于x的放在另一边的实现
    private static int partition(int[] nums, int l, int x, int r) {
        int t = l;
        int index = l;
        int xi = 0;
        while (index <= r) {
            if (nums[index] <= x) {
                swapNum(nums, index, t);
                if (x == nums[t]) {
                    xi = t;
                }
                index++;
                t++;
            } else {
                index++;
            }
        }
        swapNum(nums, xi, t - 1);
        return t - 1;
    }

    //这个方法其实就是小于x在一边，等于x在中间，大于x在另一边的实现
    private static void partition1(int[] nums, int l, int x, int r) {
        int index = l;
        int a = index;
        int b = r;
        while (index <= b) {
            if (nums[index] > x) {
                swapNum(nums, index, b);
                b--;
            } else if (nums[index] < x) {
                swapNum(nums, a, index);
                a++;
                index++;
            } else {
                index++;
            }
        }
        left = a;
        right = b;
    }

    private static void swapNum(int[] nums, int indexA, int indexB) {
        if (indexA == indexB) {
            return;
        } else {
            int temp = nums[indexA];
            nums[indexA] = nums[indexB];
            nums[indexB] = temp;
        }
    }

    private static void mergeSort(int[] nums, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        mergeAll(nums, l, mid, r);

    }

    private static void mergeAll(int[] nums, int l, int mid, int r) {
        int a = l;
        int b = mid + 1;
        int t = l;
        while (a <= mid && b <= r) {
            temp[t++] = (nums[a] < nums[b]) ? nums[a++] : nums[b++];
        }
        while (a <= mid) {
            temp[t++] = nums[a++];
        }
        while (b <= r) {
            temp[t++] = nums[b++];
        }
        for (int i = l; i <= r; i++) {
            nums[i] = temp[i];
        }
    }
}