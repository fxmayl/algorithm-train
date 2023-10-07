import java.util.ArrayList;
import java.util.List;

/**
 * 图
 *
 * @author fxm
 * @date 2023/9/28 10:08 上午
 */
public class Graph {
    private final int V; // 定点数
    private int E; // 边数
    private List<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new ArrayList[V]; // 构造邻接链表
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public int V() {
        return V;
    }

    private int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
