/**
 * leetcode 12 整数转罗马数字
 * 贪心算法的一个实例，其实我很在意，关于这个枚举倒是没什么问题，而且，他只能做有限位，所有都是写死的，就很奇怪的得到了n的复杂度
 */


public class Solution {

    public static void main(String[] args) {
        int num = 3;
        String result;
        result = intToRoman(num);
        System.out.println("result=" + result);
    }

    private static String intToRoman(int num) {
        int[] ints=new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romans=new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb=new StringBuilder();
        int index=0;
        while (index<ints.length){
            while (num>=ints[index]){
                sb.append(romans[index]);
                num-=ints[index];
            }
            index++;
        }

        return sb.toString();
    }
}
