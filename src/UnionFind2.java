/**
 * quick-union
 *
 * @author fxm
 * @date 2023/7/14 10:37 下午
 */
public class UnionFind2 {
    private int[] id;
    private int count;

    public UnionFind2(int N) {
        this.count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
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
        id[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args) {
        UnionFind2 unionFind2 = new UnionFind2(5);
        unionFind2.union(0, 1);
        unionFind2.union(0, 2);
        unionFind2.union(0, 3);
        unionFind2.union(0, 4);
        System.out.println(unionFind2.find(0));
    }
}
