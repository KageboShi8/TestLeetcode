import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode 30 串联所有单词的子串
 * 本题其实没有什么特别的技巧，主要就是滑动窗口的应用。暴力搜索可用，但是很慢，如法1
 * 而法1主要的耗时在于数组，list的插拔，非常耗时，用hashmap操作数据的插拔和比较就非常迅速了。即便仍然是暴力搜索的
 * 方法，仅仅只使用hashmap 时间的消耗减少的也很迅速。如法二
 * 而法3就是真正的滑动窗口了，使用left和right来表示窗口的两端。循环不过是循环每个单词的长度。
 * 假设，每个单词长度为3，即只需要遍历0，1，2三个位置的所有情况。在这个0，1，2位置的循环中进行整个子串的遍历，调整窗口的
 * 大小直到滑动至最后，进行3次重复便可。虽然从代码上来说，麻烦了一点。不过从速度上来书，却是飞跃性的体验。
 */

public class Solution {
    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = new String[]{"foo", "bar"};
        List<Integer> result = findSubString2(s, words);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
        System.out.println("size=" + result.size());
    }


    //ac倒是ac了 就是巨慢
    private static List<Integer> findSubString1(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words == null) return result;
        int windowLength = words[0].length() * words.length;
        if (s.length() < windowLength) return result;
        int i = 0;
        while (i <= s.length() - windowLength) {
            if (checkEqual1(s.substring(i, i + windowLength), words)) {
                result.add(i);
            }
            i++;
        }
        return result;
    }

    //使用了hashmap作为子串是否符合的判定条件，非常的高效。不过要在每次循环中起一个新的hashmap，总是很难受。
    private static List<Integer> findSubString2(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words == null || words.length == 0) return result;
        int stringLength = s.length();
        int singleLength = words[0].length();
        int numLength = words.length;
        int windowLength = singleLength * numLength;
        if (stringLength < windowLength) return result;
        Map<String, Integer> map = initMap2(numLength, words);

        for (int i = 0; i <= stringLength - windowLength; i++) {
            Map<String, Integer> stringIntegerMap = initMap2(numLength, words);
            if (checkEqual2(s.substring(i, i + windowLength), stringIntegerMap, singleLength)) {
                result.add(i);
            }
        }

        return result;
    }

    //更加优化的滑动窗口
    private static List<Integer> findSubString3(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return result;
        int size = words.length;
        int left = 0, right = 0, len = words[0].length();
        Map<String, Integer> needs = new HashMap<>();
        Map<String, Integer> windows = new HashMap<>();
        for (String word : words) {
            int value = needs.getOrDefault(word, 0);
            needs.put(word, value + 1);
        }
        int match = 0;
        for (int i = 0; i < len; i++) {
            right = left = i;
            match = 0;
            while (right <= s.length() - len) {
                String s1 = s.substring(right, right + len);
                right += len;
                Integer count = windows.getOrDefault(s1, 0);
                windows.put(s1, count + 1);
                if (needs.containsKey(s1) && windows.get(s1) == needs.get(s1)) {
                    match++;
                }

                while (left<right&&match==needs.size()){
                    if ((right-left)/len==size){
                        result.add(left);
                    }
                    String s2=s.substring(left,left+len);
                    left+=len;
                    windows.put(s2,windows.get(s2)-1);
                    if (needs.containsKey(s2)&&windows.get(s2)<(needs.get(s2))){
                        match--;
                    }
                }
            }
            windows.clear();
        }
        return result;
    }

    private static Map<String, Integer> initMap2(int numLength, String[] words) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < numLength; i++) {
            int value = map.getOrDefault(words[i], 0);
            map.put(words[i], value + 1);
        }
        return map;
    }

    private static boolean checkEqual2(String window, Map<String, Integer> map, int n) {
        for (int i = 0; i < window.length(); i += n) {
            if (map.containsKey(window.substring(i, i + n))) {
                Integer value = map.get(window.substring(i, i + n));
                if (value < 1) {
                    return false;
                } else {
                    map.put(window.substring(i, i + n), value - 1);
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean checkEmpty1(List list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != "") {
                return false;
            }
        }
        return true;
    }

    private static boolean checkEqual1(String txt, String[] words) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            list.add(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            String substring = txt.substring(i * words[0].length(), (i + 1) * words[0].length());
            for (int j = 0; j < words.length; j++) {
                if (substring.equals(list.get(j))) {
                    list.set(j, "");
                    break;
                }
            }
        }
        if (checkEmpty1(list)) {
            return true;
        } else {
            return false;
        }
    }


}
