/**
 * leetcode 8 atoi 字符串转整数
 * 其实没什么难度，理清楚逻辑，了解到char是可以直接当作数字比大小的（这就涉及char在计算机中的表示了），注意这里也有溢出问题，这种取数的操作，由于要乘除10，自然有整数溢出问题，其他就没什么了
 * 仅仅考研编码能力而已。
 */


public class Solution {
    public static void main(String[] args) {
        String str = "-+2+";
        int result = myAtoi(str);
        System.out.println("result=" + result);
    }

    private static int myAtoi(String str) {
        int len = str.length();
        int flag = 1;
        int ans = 0;
        int first = 1;
        for (int i = 0; i < len; i++) {
            char temp = str.charAt(i);
            if (first == 1) {
                if (temp == ' ') {
                    continue;
                } else if (temp == '+') {
                    flag = 1;
                    first = 0;
                } else if (temp == '-') {
                    first = 0;
                    flag = -1;
                } else if (temp >= '0' && temp <= '9') {
                    first = 0;
                    ans = ans * 10 + (int) (temp - '0');
                } else {
                    return 0;
                }
            } else {
                if (temp >= '0' && temp <= '9') {
                    if (flag == 1 && (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && temp > '7')))
                        return Integer.MAX_VALUE;
                    if (flag == -1 && (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && temp > '8')))
                        return Integer.MIN_VALUE;
                    ans = ans * 10 + (int) (temp - '0');
                } else {
                    if (temp == '-' || temp == '+') {
                        if (ans!=0){
                            return ans*flag;
                        }else {
                            return 0;
                        }
                    }

                    return flag * ans;
                }
            }
        }
        return flag * ans;
    }
}
