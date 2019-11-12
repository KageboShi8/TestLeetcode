import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode 17 电话号码字母组合
 * 最典型的回溯法 可以看作是dfs的一个例子。深度优先算法，递归用来完成深度的遍历，循环用来列举广度的情况，一般用于广度有限，深度
 * 不明的枚举，通常用递归的方法解决。。关于bfs我们未来再说当然深度优先在递归前有很多的剪枝策略，毕竟其每一个递归点就是其深度的节点
 * 在那之前进行剪枝是最合适的时机。
 */

public class Solution {
    public static void main(String[] args) {
        String numString = "23";
        List<String> result = numberCombination(numString);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    private static List<String> numberCombination(String digits) {
        Map<Character, String> dic = new HashMap<>();
        dic.put('2', "abc");
        dic.put('3', "def");
        dic.put('4', "ghi");
        dic.put('5', "jkl");
        dic.put('6', "mno");
        dic.put('7', "pqrs");
        dic.put('8', "tuv");
        dic.put('9', "wxyz");
        List<String> list = new ArrayList<>();
        if (digits.length() != 0) {
            backTrack("", digits, list, dic);
        }
        return list;
    }

    //回溯法
    private static void backTrack(String combination, String next_digits, List<String> list, Map<Character, String> dic) {
        if (next_digits.length() == 0) {
            list.add(combination);
        } else {
            String s = dic.get(next_digits.charAt(0));
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                backTrack(combination + c, next_digits.substring(1), list, dic);
            }
        }
    }

}
