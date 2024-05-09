
/*
    判断子序列
 */

public class Solution {
    public static void main(String[] args) {
        boolean res = isSubsequence("abc", "ahbgdc");
        System.out.println("res=" + res);
    }

    private static boolean isSubsequence(String s, String k) {
        int m = s.length();
        int n = k.length();
        int i=0;
        int j=0;
        while ((i<m)&&(j<n)){
           if (s.charAt(i)==k.charAt(j)){
               i++;
               j++;
           }else {
               j++;
           }
        }

        return i==m;
    }
}
