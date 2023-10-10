import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 有向图中基于深度优先搜索的顶点排序
 *
 * @author fxm
 * @date 2023/10/10 2:59 下午
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost; // 所有顶点的逆后序排列

    public DepthFirstOrder(Digraph digraph) {
        marked = new boolean[digraph.V()];
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();
        for (int s = 0; s < digraph.V(); s++) {
            if (!marked[s]) {
                dfs(digraph, s);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        pre.offer(v);
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
        post.offer(v);
        reversePost.push(v);
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(4);
        digraph.addEdge(0, 1);
        digraph.addEdge(1, 2);
        digraph.addEdge(1, 3);
        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
        System.out.println(depthFirstOrder.pre);
        System.out.println(depthFirstOrder.post);
//        System.out.println(depthFirstOrder.reversePost);
        for (int i = 0; !depthFirstOrder.reversePost.isEmpty(); i++) {
            System.out.println(depthFirstOrder.reversePost.pop());
        }
    }
}
