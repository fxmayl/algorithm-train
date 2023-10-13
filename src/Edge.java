/**
 * 边
 * 带权重的边的数据类型
 *
 * @author fxm
 * @date 2023/10/13 1:57 下午
 */
public class Edge implements Comparable<Edge> {
    private final int v; // 顶点之一
    private final int w; // 另一个顶点
    private final double weight; // 边对应的权重

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return this.weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == this.v) {
            return w;
        }
        if (vertex == this.w) {
            return v;
        }
        throw new RuntimeException("Inconsistent edge");
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }
}
