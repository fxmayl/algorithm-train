/**
 * 自底向上的归并排序
 * <p>
 * 递归实现的归并排序是算法设计中分治思想的典型应用。
 * 我们将一个大问题分割成小问题分别解决，然后用所有小问题的答案来解决整个大问题。
 * 尽管我们考虑的问题是归并两个大数组， 实际上我们归并的数组大多数都非常小。
 * > 实现归并排序的另一种方法是先归并那些微型数组，然后再成对归并得到的子数组，
 * > 如此这般，直到我们将整个数组归并在一起。这种实现方法比标准递归方法 所需要的代码量更少。
 * 1.首先我们进行的是两两归并(把每个元素想象成一个大小为 1 的数组)
 * 2.然后是四四归并(将两个大小为 2 的数组归并成一个有 4 个元素的数组)
 * 3.然后是八八的归并，一直下去。
 * 4.在每一轮归并中，最后一次归并的第二个子数组可能比第一个子数组要小(但这对 merge() 方法不是 问题)，
 *   如果不是的话所有的归并中两个数组大小都应该一样，而在下一轮中子数组的大小会翻倍。
 *
 * 对于长度为 N 的任意数组，自底向上的归并排序需要 1/2NlgN 至 NlgN 次比较，最多 访问数组 6NlgN 次。
 *
 * 没有任何基于比较的算法能够保证使用少于 lg(N!)~ NlgN 次比较将长度为 N 的数组 排序。
 *
 * @author fxm
 * @date 2023/7/21 10:32 下午
 */
public class MergeSortBU implements Sort {

    /**
     * 辅助数组
     */
    private static int[] aux;

    @Override
    public void sort(int[] a) {
        int N = a.length;
        aux = new int[N];
        // 自底向上的归并排序会多次遍历整个数组，根据子数组大小进行两两归并。
        // 子数组的大小 sz 的初始值 为 1，每次加倍。
        // 最后一个子数组的大小只有在数组大小是 sz 的偶数倍的时候才会等于 sz(否则它会比 sz 小)。
        for (int sz = 1; sz < N; sz = sz + sz) {
            // 0..1, 2..3, 4..5, 6..7...
            for (int start = 0; start < N - sz; start = start + sz + sz) {
                // start + sz - 1 表示左右两边子数组中间位置
                // start + sz + sz - 1 表示 右边子数组的最大下标
                merge(a, start, start + sz - 1, Math.min(start + sz + sz - 1, N - 1));
            }
        }
    }

    private static void merge(int[] a, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        // 优化3：
        // 我们可以节省将数组元素复制到用于归并的辅助数组所用的时间(但空间不行)。
        // 要做到这一 点我们要调用两种排序方法，一种将数据从输入数组排序到辅助数组，
        // 一种将数据从辅助数组排序 到输入数组。这种方法需要一些技巧，我们要在递归调用的每个层次交换输入数组和辅助数组的角色
        for (int k = start; k <= end; k++) {
            aux[k] = a[k];
        }
        for (int k = start; k <= end; k++) {
            if (i > mid) { // 左边子数组已经遍历完了
                a[k] = aux[j++];
            } else if (j > end) { // 右边子数组遍历完了
                a[k] = aux[i++];
            } else if (Sort.less(aux[i], aux[j])) { // 左右两边子数组还有元素存在
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 5, 4, 9, 6};
        new MergeSortBU().sort(a);
    }
}
