/**
 * 拓扑排序
 *
 * @author fangxiaoming
 * @date 2023/10/10 3:20 下午
 */
public class Topological {
    private Iterable<Integer> order; // 顶点的拓扑顺序

    public Topological(Digraph digraph) {
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        if (!directedCycle.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
            order = depthFirstOrder.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }
}
