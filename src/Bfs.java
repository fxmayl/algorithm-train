import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author : fxm
 * @program : algorithm-train
 * @description : BFS
 * BFS 算法都是用「队列」这种数 据结构
 * BFS 找到的路径一定是最短的，但代价 就是空间复杂度比 DFS 大很多
 * 问题的本质就 是让你在一幅「图」中找到从起点 start 到终点 target 的最近距离，这 个例子听起来很枯燥，但是 BFS 算法问题其实都是在干这个事儿
 * BFS算法框架：
 * // 计算从起点 start 到终点 target 的最近距离
 * int BFS(Node start, Node target) {
 * // 核心数据结构
 * Queue<Node> q;
 * // 避免走回头路
 * Set<Node> visited;
 * // 将起点加入队列
 * q.offer(start);
 * <p>
 * visited.add(start);
 * // 记录扩散的步数
 * int step = 0;
 * <p>
 * while (q not empty) {
 * <p>
 * int sz = q.size();
 * // 将当前队列中的所有节点向四周扩散 //
 * <p>
 * for(int i=0;i<sz; i++){
 * <p>
 * Node cur=q.poll();
 * <p>
 * // 划重点:这里判断是否到达终点
 * if (cur is target)
 * return step;
 * // 将 cur 的相邻节点加入队列
 * for (Node x : cur.adj()) {
 * if (x not in visited) {
 * q.offer(x);
 * visited.add(x);
 * }
 * }
 * }
 * <p>
 * // 划重点:更新步数在这里
 * step++;
 * }
 * }
 * @date : 2021-01-15 20:25
 **/
public class Bfs {

    @Test
    public void test1() {
        TreeNode root = new TreeNode();
        root.val = 3;
        TreeNode rootLeft = new TreeNode();
        rootLeft.val = 9;
        TreeNode rootRight = new TreeNode();
        rootRight.val = 20;
        root.left = rootLeft;
        root.right = rootRight;

        TreeNode rootRightLeft = new TreeNode();
        rootRightLeft.val = 15;
        TreeNode rootRightRight = new TreeNode();
        rootRightRight.val = 7;

        rootRight.left = rootRightLeft;
        rootRight.right = rootRightRight;

        System.out.println(minDepPath(root));
    }

    @Test
    public void test2() {
        System.out.println(openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202"));
    }

    @Test
    public void test3() {
        Arrays.sort(new int[]{2, 4, 9, 1, 5, 8, 3});
    }


    /**
     * 二叉树的最小高度
     *
     * @param root
     * @return
     */
    public int minDepPath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        // root 本身就是一层，depth 初始化为 1
        int depPath = 1;
        while (!q.isEmpty()) {
            int sz = q.size();

            for (int i = 0; i < sz; i++) {
                TreeNode curr = q.poll();

                if (curr == null) {
                    continue;
                }

                /* 判断是否到达终点 */
                if (curr.left == null && curr.right == null) {
                    return depPath;
                }

                /* 将 cur 的相邻节点加入队列 */
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }
            /* 这里增加步数 */
            depPath++;
        }
        return depPath;
    }

    class TreeNode {
        public TreeNode left;

        public TreeNode right;

        public int val;
    }


    /**
     * 打开转盘锁
     * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
     *
     * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     *
     * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     *
     * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
     *
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer("0000");
        visited.add("0000");

        int step = 0;

        Set<String> deads = new HashSet<>(Arrays.asList(deadends));

        while (!q.isEmpty()) {
            int sz = q.size();

            for (int i = 0; i < sz; i++) {
                String curr = q.poll();

                if (curr == null) {
                    continue;
                }

                if (target.equals(curr)) {
                    return step;
                }
                if (deads.contains(curr)) {
                    continue;
                }


                for (int j = 0; j < 4; j++) {
                    String plusOne = plusOne(curr, j);
                    if (!visited.contains(plusOne)) {
                        q.offer(plusOne);
                        visited.add(plusOne);
                    }
                    String minusOne = minusOne(curr, j);
                    if (!visited.contains(minusOne)) {
                        q.offer(minusOne);
                        visited.add(minusOne);
                    }
                }
            }

            step++;
        }
        return -1;
    }

    private String minusOne(String curr, int j) {
        char[] chars = curr.toCharArray();
        if (chars[j] == '0') {
            chars[j] = '9';
        } else {
            chars[j] -= 1;
        }
        return new String(chars);
    }

    private String plusOne(String curr, int j) {
        char[] chars = curr.toCharArray();
        if (chars[j] == '9') {
            chars[j] = '0';
        } else {
            chars[j] += 1;
        }
        return new String(chars);
    }
}
