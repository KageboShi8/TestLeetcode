import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 13 罗马数字转数字
 * 这两题其实无甚好说，其实都是不难的问题，因为有限，所以其实就是创造字典就可以了。是很常用的做法，也不难。重点在于字典的创造
 * 关键在于IV的设定
 */


public class Solution {
    public static void main(String[] args) {
        String roman = "IV";
        int result;
        result = romanToInt(roman);
        System.out.println("result=" + result);
    }

    private static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
        int index = 0;
        int len = s.length();
        int result = 0;
        while (index < len - 1) {
            String ss = s.substring(index, index + 2);
            if (map.containsKey(ss)) {
                result += map.get(ss);
                index += 2;
            } else {
                String sss = s.substring(index, index + 1);
                result += map.get(sss);
                index += 1;
            }
        }

        if (index < len) {
            String sss = s.substring(index, index + 1);
            result += map.get(sss);
        }

        return result;
    }

}
