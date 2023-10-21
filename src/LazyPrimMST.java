import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 最小生成树的 Prim 算法的延时实现
 * 延时：将失效的边先保留下来，等到要删除的时候再检查边的有效性
 * Prim 算法的这种实现使用了一条优先队列来保存所有的横切边、一个由顶点索引的数组来标记树的 顶点以及一条队列来保存最小生成树的边。
 *      这种延时实现会在优先队列中保留失效的边。
 *
 * Prim 算法的延时实现计算一幅含有 V 个顶点和 E 条边的连通加权无向图的最小生成树 所需的空间与 E 成正比，所需的时间与 ElogE 成正比(最坏情况)。
 * 证明。算法的瓶颈在于优先队列的 insert() 和 delMin() 方法中比较边的权重的次数。优先 队列中最多可能有 E 条边，这就是空间需求的上限。
 *      在最坏情况下，一次插入的成本为~ lgE， 删除最小元素的成本为~ 2lgE。因为最多只能插入 E 条边，删除 E 次 最小元素，
 *      时间上限显而易见。
 *
 * @author fxm
 * @date 2023/10/21 9:15 上午
 */
public class LazyPrimMST {
    private boolean[] marked; // 最小生成树的顶点
    private Queue<Edge> mst; // 最小生成树的边
    private PriorityQueue<Edge> pq; // 横切边(包括失效的边)

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new PriorityQueue<Edge>();
        marked = new boolean[G.V()];
        mst = new LinkedList<>();
        visit(G, 0); // 假设G是连通的
        while (!pq.isEmpty()) {
            Edge e = pq.remove(); // 从pq中得到权重最小的边
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue; // 跳过失效的边
            mst.offer(e); // 将边添加到树中
            if (!marked[v]) visit(G, v); // 将顶点(v或w)添加到树中
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) { // 标记顶点v并将所有连接v和未被标记顶点的边加入pq
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)]) pq.add(e);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     *
     * 最小生成树的 Prim 算法(即时版本)
     * Prim 算法的即时实现计算一幅含有 V 个顶点和 E 条边的连通加权无向图的最小生成树所需的空间和 V 成正比，所需的时间和 ElogV 成正比(最坏情况)。
     * 证明。因为优先队列中的顶点数最多为 V，且使用了三条由顶点索引的数组，所以所需空间的上限和 V 成正比。
     *      算法会进行 V 次插入操作，V 次删除最小元素的操作和(在最坏情况下)E次改变优先级的操作。
     *      已知在基于堆实现的索引优先队列中所有这些操作的增长数量级为 logV, 所以将所有这些加起来可知算法所需时间和 ElogV 成正比。
     *
     *  主要思想是按照边的权重顺序(从小到大)处理它们， 将边加入最小生成树中，加入的边不会与已经加入的边构成环，直到树中含有 V-1 条边为止
     *
     * public class PrimMST {
     *      private Edge[] edgeTo; // 距离树最近的边
     *      private double[] distTo; // distTo[w] = edgeTo[w].weight()
     *      private boolean[] marked; // 如果v在树中则为true
     *      private IndexMinPQ<Double> pq; // 有效的横切边
     *
     *      public PrimMST(EdgeWeightedGraph G) {
     *          edgeTo = new Edge[G.V()];
     *          distTo = new double[G.V()];
     *          marked = new boolean[G.V()];
     *          for (int v = 0; v < G.V(); v++)
     *              distTo[v] = Double.POSITIVE_INFINITY;
     *          pq = new IndexMinPQ<Double>(G.V());
     *          distTo[0] = 0.0;
     *          pq.insert(0, 0.0); // 用顶点0和权重0初始化pq
     *          while (!pq.isEmpty())
     *              visit(G, pq.delMin()); // 将最近的顶点添加到树中
     *      }
     *
     *      private void visit(EdgeWeightedGraph G, int v) {
     *          // 将顶点v添加到树中，更新数据
     *          marked[v] = true;
     *          for (Edge e : G.adj(v)) {
     *              int w = e.other(v);
     *              if (marked[w]) continue; // v-w失效
     *              if (e.weight() < distTo[w]) { // 连接w和树的最佳边Edge变为e
     *                  edgeTo[w] = e;
     *                  distTo[w] = e.weight();
     *                  if (pq.contains(w)) pq.change(w, distTo[w]);
     *                  else                pq.insert(w, distTo[w]);
     *              }
     *          }
     *      }
     *  }
     */
}
