import java.util.ArrayList;
import java.util.List;

/**
 * 加权有向图的数据类型
 *
 * @author fxm
 * @date 2023/10/21 10:25 下午
 */
public class EdgeWeightedDigraph {
    private final int V; // 顶点总数
    private int E; // 边的总数
    private final List<DirectedEdge>[] adj; // 邻接表

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new ArrayList[V];
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

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        List<DirectedEdge> edges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (DirectedEdge w : this.adj(i)) {
                edges.add(w);
            }
        }
        return edges;
    }
}
