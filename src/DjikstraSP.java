import java.util.Stack;

/**
 * 最短路径的Djikstra算法
 * 边的权重必须为正
 * <p>
 * 在一幅含有V个顶点和E条边的加权有向图中，使用Djikstra算法计算根结点为给定起点的最短路径树所需的空间与V成正比，时间与ElogV成正比(最坏情况下)
 * <p>
 * Dijkstra 算法的实现每次都会为最短路径树添加一条边，该边由一个树中的顶点指向一个非树 顶点 w 且它是到 s 最近的顶点。
 *
 * @author fxm
 * @date 2023/10/23 11:18 下午
 */
public class DjikstraSP {
    private final DirectedEdge[] edgeTo;
    private final double[] distTo;
    private final IndexMinPQ<Double> pq;

    public DjikstraSP(EdgeWeightedDigraph digraph, int s) {
        edgeTo = new DirectedEdge[digraph.V()];
        distTo = new double[digraph.V()];
        pq = new IndexMinPQ<>(digraph.V());

        for (int i = 0; i < digraph.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        distTo[s] = 0d;
        pq.insert(s, 0d);

        while (!pq.isEmpty()) {
            relax(digraph, pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph digraph, int v) {
        for (DirectedEdge e : digraph.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.change(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}
