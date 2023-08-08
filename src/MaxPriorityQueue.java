/**
 * 堆 - 大顶堆
 * <p>
 * 对于一个含有 N 个元素的基于堆的优先队列，插入元素操作只需不超过(lgN+1)次比较， 删除最大元素的操作需要不超过 2lgN 次比较。
 * <p>
 * 问 为什么在堆的表示中不使用 a[0] ?
 * 答 这么做可以稍稍简化计算。实现从 0 开始的堆并不困难，a[0] 的子结点是 a[1] 和 a[2]，a[1] 的
 * 子结点是 a[3] 和 a[4]，a[2] 的子结点是 a[5] 和 a[6]，以此类推。但大多数程序员更喜欢我们的
 * 简单方法。另外，将 a[0] 的值用作哨兵(作为 a[1] 的父结点)在某些堆的应用中很有用。
 *
 * @author fxm
 * @date 2023/8/3 2:09 下午
 */
public class MaxPriorityQueue implements Sort {
    private int[] pq;
    private int N;

    public MaxPriorityQueue() {
    }

    // 使用 N + 1作为数组大小， 舍弃第0位的空间， 从1开始
    // 方便 下沉和上浮操作的处理
    public MaxPriorityQueue(int N) {
        pq = new int[N + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(int data) {
        pq[++N] = data;
        swin(N);
    }

    public int delMax() {
        int max = pq[1]; // 堆顶部是最大的元素
        Sort.exchange(pq, 1, N); // 将其和最后一个结点交换
        N--; // 删除最后一个元素
        sink(1);
        return max;
    }

    /**
     * 下沉，删除元素时，将数组最后的一个元素交换到数组的第一个位置
     * 此时为了调整堆的状态，需要将第一个元素进行下沉
     */
    private void sink(int i) {
        while (2 * i <= N) {
            int j = 2 * i;
            if (j < N && Sort.less(pq[j], pq[j + 1])) {
                j++;
            }
            if (!Sort.less(pq[i], pq[j])) {
                break;
            }
            Sort.exchange(pq, i, j);
            i = j;
        }
    }

    /**
     * 上浮，插入的元素要上浮到合适的位置
     *
     * @param index
     */
    private void swin(int index) {
        while (index > 1 && Sort.less(pq[index / 2], pq[index])) {
            Sort.exchange(pq, index / 2, index);
            index = index / 2;
        }
    }

    /**
     * 堆排序
     * <p>
     * 用下沉操作由 N 个元素构造堆只需少于 2N 次比较以及少于 N 次交换。
     *
     * @param a
     */
    @Override
    public void sort(int[] a) {
        int N = a.length;
        for (int i = N / 2; i >= 1; i--) {
            sink(a, i, N);// 下沉之后， 会把大数据放到最开始
        }
        while (N > 1) { // 循环之后将大数据都放到了后面，小数据前移
            exchange(a, 1, N--); // 将最大数交换到数组末尾，将末尾的数据交换到第一位
            sink(a, 1, N); // 将第一位数据进行下沉，但是此时的范围为 1 ～ (N-1)也就是倒数第二个元素
        }
    }

    /**
     * 下沉时，传入的操作是从 下标1 开始的，而不是从下标 0开始的
     * 所以在比较和交换的时候需要将索引下标减去1
     */
    private void sink(int[] pq, int i, int n) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && less(pq, j, j + 1)) {
                j++;
            }
            if (!less(pq, i, j)) {
                break;
            }
            exchange(pq, i, j);
            i = j;
        }
    }

    /**
     * 因为堆的数组是从 1 开始 ，所以 需要从传入的数组下标中减去1进行处理
     */
    private boolean less(int[] a, int i, int j) {
        return a[i - 1] < a[j - 1];
    }

    private void exchange(int[] a, int i, int j) {
        int v = a[j - 1];
        a[j - 1] = a[i - 1];
        a[i - 1] = v;
    }

    public static void main(String[] args) {
        MaxPriorityQueue maxPriorityQueue = new MaxPriorityQueue(5);
        maxPriorityQueue.insert(3);
        maxPriorityQueue.insert(8);
        System.out.println(maxPriorityQueue.delMax());
        maxPriorityQueue.insert(2);
        maxPriorityQueue.insert(5);
        System.out.println(maxPriorityQueue.delMax());
        System.out.println(maxPriorityQueue.delMax());

        int[] a = {3, 2, 1, 5, 4, 9, 6};
        maxPriorityQueue.sort(a);
    }
}
