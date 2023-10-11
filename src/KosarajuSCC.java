import java.util.Stack;

/**
 * 计算有向图强连通分量的 Kosaraju 算法
 * 在CC的基础之上处理的
 *
 * @author fxm
 * @date 2023/10/11 11:05 上午
 */
public class KosarajuSCC {
    private boolean[] marked; // 已访问过的顶点
    private int[] id; // 强连通分量的标识符
    private int count; // 强连通分量的数量

    public KosarajuSCC(Digraph digraph) {
        marked = new boolean[digraph.V()];
        id = new int[digraph.V()];
        // 使用 DepthFirstOrder 来计算它的反向图 GR 的逆后序排列
        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph.reverse());
        Stack<Integer> reversePost = depthFirstOrder.reversePost();
        for (; !reversePost.isEmpty(); ) {
            Integer s = reversePost.pop();
            if (!marked[s]) {
                dfs(digraph, s);
                count++;
            }
        }
    }

    // 所有在同一个递归 dfs() 调用中被访问到的顶点都在同一个强连通分量中
    private void dfs(Digraph digraph, int s) {
        marked[s] = true;
        id[s] = count;
        for (int w : digraph.adj(s)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }
}
