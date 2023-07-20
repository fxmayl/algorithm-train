/**
 * @author fxm
 * @date 2023/7/14 10:37 下午
 */
public class UnionFind1 {
    private int[] id; // 分量id(以触点作为索引)
    private int count; // 分量数量

    public UnionFind1(int N) {
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
        return id[p];
    }

    public void union(int p, int q) {
        // 将p和q归并到相同的分量中
        int pId = find(p);
        int qId = find(q);
        // 如果p和q已经在相同的分量之中则不需要采取任何行动
        // pID和qID都是整形值，是固定的
        if (pId == qId) {
            return;
        }
        // 将p的分量重命名为q的名称
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
        count--;
    }

    /**
     * 答案：
     * 但题目中给的的id[p]和id[q]的值不一定固定。 在if后如果id[p]发生了改变，那么这个等式就可能出问题 id[p]可能会发生变化
     * id 0 0 0 0 0 5 5 5 5 5
     * 输入 0, 5
     * i = 0 时，id[i] == id[p]，此时 id[i] = id[q]。
     * 数组变为 5 0 0 0 0 5 5 5 5 5
     * i = 1 时，id[i] != id[p]，算法出现错误。
     * 如果在 id[p] 之后还有需要修改的元素，那么这个算法就会出现错误。
     *
     * 问题：
    用一个反例证明 quick-find 算法中的 union() 方法的以下直观实现是错误的:
    public void union(int p, int q)
    {
        if (connected(p, q)) return;
        // 将p的分量重命名为q的分量
        for (int i = 0; i < id.length; i++)
            // 在这个判断的地方，当i等于p的时候，会引起id[p]发生改变
            if (id[i] == id[p]) id[i] = id[q];
        count--;
    }
     **/
}
