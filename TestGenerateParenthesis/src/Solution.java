import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode 22 括号生成
 * 方法1和方法2是深度优先遍历  非常常用 一个从大到小，一个从小到大而已
 * 方法3使用的是广度优先遍历 在此题中比深度优先代码复杂一些。层序遍历一般来说要完整的一层层的构造整个树结构，然后再在整个树中找到
 * 解空间，此题中，解空间就是最后一层子叶；
 * 方法4是闭合数 真的是不太明白
 */

public class Solution {

    static class Node {
        private String res;
        private int l;
        private int r;

        public Node(String res, int l, int r) {
            this.res = res;
            this.l = l;
            this.r = r;
        }
    }

    public static void main(String[] args) {
        List<String> result = generateParenthesis4(2);
        outList(result);
    }

    private static List<String> generateParenthesis4(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int i = 0; i <= n-1; i++) {
                for (String left : generateParenthesis4(i)) {
                    for (String right : generateParenthesis4(n - 1 - i)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        return ans;
    }

    //bfs和dfs其实都是一个树的遍历过程，只不过dfs从深度开始回溯，基本和回溯法一个思路。
    //而bfs则是广度开始，可以理解为层序遍历，一层一层向下遍历，此题中，由于解注定在最后一层，所以代码量稍微大一些。
    private static List<String> generateParenthesis3(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));
        n *= 2;
        while (n > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                if (current.l > 0) {
                    queue.offer(new Node(current.res + "(", current.l - 1, current.r));
                }
                if (current.r > 0 && current.l < current.r) {
                    queue.offer(new Node(current.res + ")", current.l, current.r - 1));
                }
            }
            n--;
        }
        //遍历最后一层
        while (!queue.isEmpty()) {
            result.add(queue.poll().res);
        }
        return result;
    }

    private static List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        dfs2("", 0, 0, n, result);
        return result;
    }

    private static void dfs2(String s, int l, int r, int n, List<String> result) {
        if (l == n && r == n) {
            result.add(s);
            return;
        }
        if (l < n) {
            dfs2(s + "(", l + 1, r, n, result);
        }
        if (r < l && r < n) {
            dfs2(s + ")", l, r + 1, n, result);
        }
    }

    private static List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        dfs1("", n, n, result);

        return result;
    }

    private static void dfs1(String s, int l, int r, List<String> result) {
        if (l == 0 && r == 0) {
            result.add(s);
            return;
        }
        if (l > 0) {
            dfs1(s + "(", l - 1, r, result);
        }
        if (r > 0 && r > l) {
            dfs1(s + ")", l, r - 1, result);
        }
    }


    private static void outList(List<String> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
    }
}
