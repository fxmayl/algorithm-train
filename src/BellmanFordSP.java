import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 基于队列的 Bellman-Ford 算法
 *
 * 不能存在负权重环,适用领域广泛
 *
 * @author fxm
 * @date 2023/10/26 11:23 下午
 */
public class BellmanFordSP {
    private double[] distTo; // 起点到达某个顶点的路径长度
    private DirectedEdge[] edgeTo; // 起点到达某个顶点的最后一条边
    private boolean[] onQ; // 该顶点是否存在于队列中
    private Queue<Integer> queue; // 正在被放松的顶点
    private int cost; // relax()的调用次数
    private Iterable<DirectedEdge> cycle; // edgeTo[]中的是否有负权重环

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new LinkedList<>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        queue.offer(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.poll();
            onQ[v] = false;
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) { // “Bellman-Ford算法的放松操作”
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.offer(w);
                    onQ[w] = true;
                }
            }
            if (++cost % G.V() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) {
                    return;  // found a negative cycle
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

    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++)
            if (edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);

        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }

    public boolean hasNegativeCycle() { // 是否含有负权重环
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle() { // 得到负权重环 (如果没有则 返回 null)
        return cycle;
    }

    public static class EdgeWeightedDirectedCycle {
        private boolean[] marked;             // marked[v] = has vertex v been marked?
        private DirectedEdge[] edgeTo;        // edgeTo[v] = previous edge on path to v
        private boolean[] onStack;            // onStack[v] = is vertex on the stack?
        private Stack<DirectedEdge> cycle;    // directed cycle (or null if no such cycle)

        /**
         * Determines whether the edge-weighted digraph {@code G} has a directed cycle and,
         * if so, finds such a cycle.
         *
         * @param G the edge-weighted digraph
         */
        public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
            marked = new boolean[G.V()];
            onStack = new boolean[G.V()];
            edgeTo = new DirectedEdge[G.V()];
            for (int v = 0; v < G.V(); v++)
                if (!marked[v]) dfs(G, v);

            // check that digraph has a cycle
            assert check();
        }

        // check that algorithm computes either the topological order or finds a directed cycle
        private void dfs(EdgeWeightedDigraph G, int v) {
            onStack[v] = true;
            marked[v] = true;
            for (DirectedEdge e : G.adj(v)) {
                int w = e.to();

                // short circuit if directed cycle found
                if (cycle != null) return;

                    // found new vertex, so recur
                else if (!marked[w]) {
                    edgeTo[w] = e;
                    dfs(G, w);
                }

                // trace back directed cycle
                else if (onStack[w]) {
                    cycle = new Stack<DirectedEdge>();

                    DirectedEdge f = e;
                    while (f.from() != w) {
                        cycle.push(f);
                        f = edgeTo[f.from()];
                    }
                    cycle.push(f);

                    return;
                }
            }

            onStack[v] = false;
        }

        /**
         * Does the edge-weighted digraph have a directed cycle?
         *
         * @return {@code true} if the edge-weighted digraph has a directed cycle,
         * {@code false} otherwise
         */
        public boolean hasCycle() {
            return cycle != null;
        }

        /**
         * Returns a directed cycle if the edge-weighted digraph has a directed cycle,
         * and {@code null} otherwise.
         *
         * @return a directed cycle (as an iterable) if the edge-weighted digraph
         * has a directed cycle, and {@code null} otherwise
         */
        public Iterable<DirectedEdge> cycle() {
            return cycle;
        }


        // certify that digraph is either acyclic or has a directed cycle
        private boolean check() {

            // edge-weighted digraph is cyclic
            if (hasCycle()) {
                // verify cycle
                DirectedEdge first = null, last = null;
                for (DirectedEdge e : cycle()) {
                    if (first == null) first = e;
                    if (last != null) {
                        if (last.to() != e.from()) {
                            System.err.printf("cycle edges %s and %s not incident\n", last, e);
                            return false;
                        }
                    }
                    last = e;
                }

                // cycle() contains no edges
                if (first == null || last == null) {
                    System.err.printf("cycle contains no edges\n");
                    return false;
                }

                // first and last edges in cycle are not incident
                if (last.to() != first.from()) {
                    System.err.printf("cycle edges %s and %s not incident\n", last, first);
                    return false;
                }
            }


            return true;
        }
    }
}
