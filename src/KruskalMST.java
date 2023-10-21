import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 最小生成树的 Kruskal 算法
 * <p>
 * 主要思想是按照边的权重顺序(从小到大)处理它们， 将边加入最小生成树中，加入的边不会与已经加入的边构成环，直到树中含有 V-1 条边为止
 * Kruskal算法能够计算任意加权连通图的最小生成树
 * <p>
 * Prim算法是一条边一条边地来构造最小生成树, 每一步都为一棵树添加一条边。
 * Kruskal算法构造最小生成树的时候也是一条边一条边地构造, 但不同的是它寻找的边会连接一片森林中的两棵树。
 * 我们从一片由V棵单顶点的树构成的森林开始并不断将两棵树合并(用可以找到的最短边)直到只剩下一棵树, 它就是最小生成树。
 * <p>
 * Kruskal算法的计算一幅含有V个顶点和E条边的连通加权无向图的最小生成树所需的空间和E成正比,所需的时间和ElogE成正比(最坏情况)。
 * 说明：算法的实现在构造函数中使用所有边初始化优先队列，成本最多为 E 次比较，优先队列构造完成后，其余的部分和 Prim 算法完全相同。
 *      优先队列中最多可能含有 E 条边， 即所需空间的上限。
 *      每次操作的成本最多为 2lgE 次比较，这就是时间上限的由来。
 *      Kruskal算法最多还会进行E次 connected() 和 V 次 union() 操作，但这些成本相比 ElogE 的总时间的 增长数量级可以忽略不计
 * <p>
 * Kruskal算法一般还是比 Prim 算法要慢，因为在处理每条边时除了两种算法都要 完成的优先队列操作之外，它还需要进行一次 connect() 操作
 *
 * 这份 Kruskal 算法的实现使用了一条队列来保存最小生成树中的所有边、一条优先队列来保存还未被检查的边和一个 union-find 的数据结构来判断无效的边。
 * 最小生成树的所有边会按照权重的升序返回给用例
 *
 * @author fxm
 * @date 2023/10/21 10:48 上午
 */
public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
//        mst = new LinkedList<>();
//        PriorityQueue<Edge> pq = new PriorityQueue<>();
//        for (Edge e : G.edges()) {
//            pq.insert(e);
//        }
//        UF uf = new UF(G.V());
//        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
//            Edge e = pq.remove(); // 从pq得到权重最小的边和它的顶点
//            int v = e.either(), w = e.other(v);
//            if (uf.connected(v, w)) continue; // 忽略失效的边
//            uf.union(v, w); // 合并分量
//            mst.add(e); // 将边添加到最小生成树中
//        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}
