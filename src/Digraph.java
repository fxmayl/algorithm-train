import java.util.ArrayList;
import java.util.List;

/**
 * 有向图
 *
 * @author fangxiaoming
 * @date 2023/10/10 11:20 上午
 */
public class Digraph {
    private final int V;
    private int E;
    private List<Integer>[] adj;

    public Digraph(int V) {
        adj = new ArrayList[V];
        this.V = V;
        this.E = 0;
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
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph res = new Digraph(V);
        for (int s = 0; s < V; s++) {
            for (int w : adj(s)) {
                res.addEdge(w, s);
            }
        }
        return res;
    }
}
