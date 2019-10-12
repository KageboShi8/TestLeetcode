


/**
 * leetcode 5 Longest Palindromic Substring
 * 本题是要找最长回文子串 下图提供了4种解法思路。最直接的就是方法2，直接列举所有可能性，然后每个字符串全列一遍，一一比较，得到答案。答案肯定是能得到的，时间复杂度是O（n^3）简直辣眼睛。
 * 从一开始，我就没想到这种方法，毕竟，从字面上看，这种方法就有极大的复杂度，也有极大的冗余，很明显很多串都不用考虑，最简单的，比当前最长回文串短的串就不用考虑，反正就照着这个思路优化，
 * 不过也优化不到O(n^2).
 * 然后是动态规划，说是叫动态规划，其实我一点也不明白什么叫做动态规划。虽然这个题的思路很简单，对动态规划整体的理解还得放到算法看完之后。方法3就是动态规划，时间复杂度可以到O(n^2),思路如下。
 * 本题旨在找到回文子串，而回文子串的内部，斩头去尾一定也是回文子串，即s(l,r)为回文串时，s(l+1,r-1)也是回文串，以此类推，直到字串长度为0或者为1时，那么这个回文串就成立了。
 * 使用二维数组来记录每一个s(l,r)的真伪情况，当s(l,r)为真时，只考虑s(l-n,r+n)的情况就好，因为，内部必然已经成立了，而且此处，r必大于l，判定条件就是他的内部字串是否为true，以及边界上字符是否相等
 * 有一点像反过来的中心扩展法。当二维数组填满了（实际上只有一半，l<=r），问题也就解决了，所以至多有二分之平方的复杂度，非常的优秀。
 * 当然这题还有更优秀的算法，叫做中心拓展法。思路甚至比动态规划还要好想到，首先，回文串有两种，奇数长度，一种是偶数长度。奇数长度的话，即标定中心，然后向两边扩展，扩展到边界不等，就是此标定中心的
 * 最大半径了，然后遍历一遍String，就得到最大半径和标定中心位置了。也就的到了最长回文串。最多也就是n^2的复杂度，偶数串情况，中心两者必相等，取两个相等的开始左右扩展，直到最大不等边界就可以了，也最多
 * 是n^2的复杂度，然后两者串长比较，就得到了结果。方法0就是这个思路，只不过合并了一下奇数串和偶数串的情况而已，看起来更加简洁。
 * 最后的最后 是专为回文串设计的manacher算法，思路也从中心拓展法而来，因为中心拓展法要分奇偶，很麻烦，于是我们改装串，将串间插上串外字符，这样整个串长就必然是奇数了，然后同样中心拓展，得到最大不等边界
 * 然后就同上了，最后出结果的时候，去掉多余字符就可以了。见方法1.关键的关键在于辅助rad[i]的获取，rad[i]中存的是每个位置的回文半径，如果此处想要O（n）的复杂度，在遍历时就应该以常数复杂度获取回文半径，
 * 而每个位置获取半径的方法，就是这个算法的关键。 此处有一个一句两句讲不清，参见有道云动态规划那篇文章，讲清楚了此处的动态规划和manacher是如何运作的。
 */
public class Solution {
    public static void main(String[] args) {
        String s = new String("abcbd");
        String result = longestPalindrome1(s);
        System.out.println("result=" + result);
    }


    //动态规划
    private static String longestPalindrome3(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        int longestPalindrome = 1;
        String longestPalindromeStr = s.substring(0, 1);
        boolean[][] dp = new boolean[len][len];
        for (int r = 1; r < len; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > longestPalindrome) {
                        longestPalindrome = r - l + 1;
                        longestPalindromeStr = s.substring(l, r + 1);
                    }
                }
            }
        }


        return longestPalindromeStr;
    }

    //暴力破解法，列举所有的可能性，一一比较,O(n^3)
    private static String longestPalindrome2(String s) {
        String ans = "";
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                System.out.println(s.substring(i, j));
                String test = s.substring(i, j);
                if (isPalindrome(test) && test.length() > max) {
                    ans = test;
                    max = Math.max(max, test.length());
                }
            }
        }

        return ans;
    }

    private static boolean isPalindrome(String test) {
        for (int i = 0; i <= test.length() / 2; i++) {
            if (test.charAt(i) != test.charAt(test.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }


    // manacher算法  这个算法写的是没问题，问题在于插入数据和删除数据时的复杂度要如何控制，这里的复杂度过高了，不过即便使用辅助数组，也只是获得了回文半径，如果要去掉加入的符号，
    // 还是需要一次遍历以解决问题的，不过，遍历这种事情应该只是O（n），问题在于其中的一次for套一次while真的是O（n）么，虽然我也不觉得是O（n^2）,但真的是O（n）么
    private static String longestPalindrome1(String s) {
        if (s.length() == 1) {
            return s;
        }
        String s1 = stringModify(s);
        // rad[i]表示以i为中心的回文的最大半径，i至少为1，即该字符本身
        int[] rad = new int[s1.length()];
        // right表示已知的回文中，最右的边界的坐标
        int right = -1;
        // id表示已知的回文中，拥有最右边界的回文的中点坐标
        int id = -1;
        // 2.计算所有的rad
        // 这个算法是O(n)的，因为right只会随着里层while的迭代而增长，不会减少。
        for (int i = 0; i < s1.length(); i++) {
            // 2.1.确定一个最小的半径
            int r = 1;
            if (i <= right) {
                r = Math.min(rad[id] - i + id, rad[2 * id - i]);
            }
            // 2.2.尝试更大的半径
            while (i - r >= 0 && i + r < s1.length() && s1.charAt(i - r) == s1.charAt(i + r)) {
                r++;
            }
            // 2.3.更新边界和回文中心坐标
            if (i + r - 1 > right) {
                right = i + r - 1;
                id = i;
            }
            rad[i] = r;
        }

        String s2 = generateResult(s1, rad);

        return s2;

    }

    private static String generateResult(String s1, int[] rad) {
        int maxLength = 0;
        int index = 0;
        for (int i = 0; i < rad.length; i++) {
            if (rad[i] > maxLength) {
                maxLength = rad[i];
                index = i;
            }
        }
        return s1.substring(index - maxLength+1, index + maxLength).replace("#", "");
    }

    private static String stringModify(String s) {
        StringBuffer sb = new StringBuffer();
        sb.append("#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append("#");
        }
        return sb.toString();
    }

    private static int checkPalindrome(String s, int index) {
        int i = 1;
        int result = 0;
        while (index - i >= 0 && index + i < s.length()) {
            if (s.charAt(index - i) == s.charAt(index + i)) {
                result = i;
                i++;
            } else {
                return result;
            }
        }
        return result;
    }


    //此种算法叫做中心扩展算法，其实就是为了分奇偶，启用一个中心向两边拓展还是两个相同的字符向两边拓展的问题
    private static String longestPalindrome0(String s) {
        if (s == null || s.length() < 1) return "";
        int end = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
