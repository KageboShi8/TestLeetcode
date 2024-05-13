import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 119 杨辉三角II
 * 要考虑倒叙缩减空间的意义和用法
 */
public class Solution {
    public static void main(String[] args) {
        List<Integer> res = getRow(3);
        System.out.println(Arrays.toString(res.toArray()));
    }

    private static List<Integer> getRow(int rowIndex) {


        List<Integer> list = new ArrayList<>(rowIndex + 1);
        list.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            list.add(0);
            for (int j = i; j > 0; j--) {
                list.set(j, list.get(j) + list.get(j - 1));
            }
        }

        return list;
    }
}
