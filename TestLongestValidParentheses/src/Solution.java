import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * leetcode 32 最长有效括号
 */


public class Solution {
    static HashMap<Character, Character> map = new HashMap();

    public static void main(String[] args) {
        String s = "())";
        int result = longestValidParentheses(s);
        System.out.println(result);
    }

    //辅助栈法
    private static int longestValidParentheses(String s) {
        int result = 0;
        Stack<Integer> stack = new Stack();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    result = Math.max(result, i - stack.peek());
                }
            }
        }

        return result;
    }

    //使用两个计数器 不需要O(n)的空间复杂度 就很简单的思路，不过要左右各来一遍
    private static int longestValidParentheses1(String s) {
        int left = 0;
        int right = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    left++;
                    break;
                case ')':
                    right++;
                    break;
            }
            if (left == right) {
                result = Math.max(result, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            switch (s.charAt(i)) {
                case '(':
                    left++;
                    break;
                case ')':
                    right++;
                    break;
            }
            if (left == right) {
                result = Math.max(result, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }

        return result;

    }

    //动态规划

}
