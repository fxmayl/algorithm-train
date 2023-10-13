import java.util.ArrayList;
import java.util.List;

/**
 * 加权无向图的数据类型
 *
 * @author fxm
 * @date 2023/10/13 2:03 下午
 */
public class EdgeWeightedGraph {
    private final int V; // 顶点总数
    private int E; // 边总数
    private List<Edge>[] adj; // 邻结表

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }
}
