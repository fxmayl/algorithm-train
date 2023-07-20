/**
 * 加权 quick-union
 *
 * @author fxm
 * @date 2023/7/14 10:37 下午
 * 带有权重，将小树连接到大树上
 * 对于加权 quick-union 算法和 N 个触点，在最坏情况下find()、connected()和 union() 的成本的增长数量级为 logN。
 * 性质：对于 N 个触点，加权 quick-union 算法构造的森林中的任意节点的深度最多为 lgN。
 * 证明。我们可以用归纳法证明一个更强的命题，即森林中大小为 k 的树的高度最多为 lgk。在
 *      原始情况下，当 k 等于 1 时树的高度为 0。根据归纳法，我们假设大小为 i 的树的高度最多为 lgi，
 *      其中 i<k。设 i ≤ j 且 i+j=k，当我们将大小为 i 和大小为 j 的树归并时，
 *      小树中的所有节点的深度增加了 1， 但它们现在所在的树的大小为 i+j=k，而 1+lgi=lg(i+i) ≤ lg(i+j)=lgk，性质成立。
 */
public class UnionFind3 {
    private int[] id; // 分量id(以触点作为索引)
    private int count; // 分量数量
    private int[] sz; // (由触点索引的)各个根节点所对应的分量的大小

    public UnionFind3(int N) {
        this.count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        // 找出分量的名称
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        // 将p和q的根节点统一
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        // 将小树连接到大树上
        if (sz[pRoot] > sz[qRoot]) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        count--;
    }

    public static void main(String[] args) {
        UnionFind3 unionFind2 = new UnionFind3(5);
        unionFind2.union(0, 1);
        unionFind2.union(0, 2);
        unionFind2.union(0, 3);
        unionFind2.union(0, 4);
        System.out.println(unionFind2.find(0));
    }
}
