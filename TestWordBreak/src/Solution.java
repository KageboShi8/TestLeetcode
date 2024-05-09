import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * leetcode 139 单词拆分  回溯 动态规划
 * 这里谢了DFS和动态规划，BFS的方法还没写~
 */

public class Solution {
    public static void main(String[] args) {
        String s = "applepenapple";
        List list = new ArrayList<String>();
        list.add("apple");
        list.add("pen");
//        boolean res = wordBreak(s, list);
        boolean res1 = wordBreak1(s, list);
        System.out.println(res1);
    }

    //回溯 DFS
    private static boolean wordBreak1(String s, List wordDict) {
        return DFSBreak(s, 0, wordDict, new HashSet<Integer>());
    }

    private static boolean DFSBreak(String s, int start, List wordDict, HashSet<Integer> indexSet) {
        if (start == s.length()) {
            return true;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            if (indexSet.contains(i)) {
                continue;
            }
            if (!wordDict.contains(s.substring(start, i))) {
                indexSet.add(i);
            } else if (DFSBreak(s, i, wordDict, indexSet)) {
                indexSet.add(i);
                return true;
            } else {
                indexSet.add(i);
            }
        }
        return false;
}


    //动态规划
    private static boolean wordBreak(String s, List wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }


        return dp[len];
    }


}
