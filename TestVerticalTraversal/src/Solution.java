import java.util.*;

/**
 * leetcode 987 二叉树的垂序遍历  实际上从垂直遍历的角度来看，本题很简单，不过是层序遍历的一点变化而已。层序遍历是分层装入容器，垂直遍历不过是竖直分层装入容器而已。
 * 稍微健壮一点使用xy的点阵描述树叶的位置再依要求装入容器就可以。本题的问题在于编码上的难度。因为一个叶子至少三个描述内容，x,y,val.所以构造出辅助类就是第一步。因为垂序遍历
 * 的话要考虑在装箱后的list中的y的大小的排序，而且也要考虑y的本身位置的排序。也就是得到结果后仍然需要x，y，val三个值进行结果处理。就增加了代码量和代码的复杂度。
 * 顺便一提，正好在这题中大量使用了Collections中的比较器，正好又学习了一下。
 */

public class Solution {


    public static class TreeNode {
        int val;

        public TreeNode(int val) {
            this.val = val;
        }

        TreeNode left;
        TreeNode right;
    }

    public static void main(String[] args) {
        TreeNode node = initTree();
        List<List<Integer>> result = verticalTraversal(node);

        outList(result);
    }

    //dfs似乎可用
    private static List<List<Integer>> verticalTraversal(TreeNode root) {
        //第一个值是x，第二个值是pair-value组 为了好垂序
        Map<Integer, List<Position>> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        map = verticalTraversalHelper(root, 0, 0, map);

        //处理这个map
        result = handleMap(map);
        return result;
    }

    //善后处理
    private static List<List<Integer>> handleMap(Map<Integer, List<Position>> map) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> keySet = map.keySet();
        List<Integer> xList = new LinkedList<>(keySet);
        Collections.sort(xList);
        for (int i = 0; i < xList.size(); i++) {
            List<Integer> sonList = new LinkedList<>();
            List<Position> positions = map.get(xList.get(i));
            Collections.sort(positions, new Comparator<Position>() {
                @Override
                public int compare(Position o1, Position o2) {
                    if (o1.y == o2.y) {
                        return o1.val > o2.val ? 1 : -1;
                    } else {
                        return o1.y > o2.y ? -1 : 1;
                    }
                }
            });
            for (int j = 0; j < positions.size(); j++) {
                sonList.add(positions.get(j).val);
            }
            result.add(sonList);
        }
        return result;
    }

    //递归本体
    private static Map<Integer, List<Position>> verticalTraversalHelper(TreeNode root, int x, int y, Map<Integer, List<Position>> map) {
        if (root == null) {
            return map;
        }
        map = addPosition(x, y, map, root.val);
        verticalTraversalHelper(root.left, x - 1, y - 1, map);
        verticalTraversalHelper(root.right, x + 1, y - 1, map);
        return map;
    }

    private static Map addPosition(int x, int y, Map<Integer, List<Position>> map, int val) {
        Position position = new Position(x, y, val);
        if (map.containsKey(x)) {
            map.get(x).add(position);
        } else {
            List<Position> list = new ArrayList<>();
            list.add(position);
            map.put(x, list);
        }
        return map;
    }


    private static void outList(List<List<Integer>> result) {
        System.out.println(result.size() + "size");
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j));
            }
            System.out.println("");
        }
    }

    private static TreeNode initTree() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);


        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        return node1;
    }

    public static class Position {

        public int x;
        public int y;
        public int val;

        public Position(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }


    }
}
