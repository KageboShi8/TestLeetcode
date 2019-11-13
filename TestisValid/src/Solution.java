
import java.util.HashMap;
import java.util.Stack;

/**
 * leetcode 20 有效的括号
 * 本题看似可以分治法，其实并不是的，括号的形式过于复杂，不适合分治，使用辅助栈比较科学；
 */


public class Solution {
    private static HashMap<Character, Character> map = new HashMap();

    public static void main(String[] args) {
        String brackets = "}";
        boolean result = isValid(brackets);
        System.out.println(result);
    }

    private static boolean isValid(String s) {
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                stack.push(map.get(s.charAt(i)));
            } else if (!stack.empty() && stack.pop().equals(s.charAt(i))) {
//                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }


}
