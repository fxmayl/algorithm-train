/**
 * 判断图是否为二分图
 * 图的双色问题
 *
 * @author fxm
 * @date 2023/10/7 11:12 上午
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColor = true;
    public TwoColor(Graph graph) {
        marked = new boolean[graph.V()];
        color = new boolean[graph.V()];
        for (int s = 0; s < graph.V(); s++) {
            if (!marked[s]) {
                dfs(graph, s);
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(graph, w);
            } else if (color[w] == color[v]) {
                isTwoColor = false;
            }
        }
    }

    public boolean isBipartite() {
        return isTwoColor;
    }
}
