/**
 * 判断图是否存在环
 *
 * @author fxm
 * @date 2023/10/7 10:44 上午
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph graph) {
        marked = new boolean[graph.V()];
        for (int s = 0; s < graph.V(); s++) {
            if (!marked[s]) {
                dfs(graph, s, s);
            }
        }
    }

    private void dfs(Graph graph, int v, int u) { // v参数表示此次调用的出发节点，u表示上一次调用时的出发节点
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (!marked[w]) { // 当此顶点没有被访问过，会继续递归调用，以w为出发点进行深入
                dfs(graph, w, v);
            } else if (w != u) { // 如果已经访问过，同时与上一次调用时的出发节点不一致，则认为有环
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 2);
        System.out.println(new Cycle(graph).hasCycle());
    }
}
