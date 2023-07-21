/**
 * 自顶向下的归并排序
 * <p>
 * 要对子数组 a[start..end] 进行排序，先将它分为 a[start..mid] 和 a[mid+1..end] 两部分，分别通过
 * 递归调用将它们单独排序，最后将有序的子数组归并为最终的排序结果。
 * <p>
 * 对于长度为 N 的任意数组，自顶向下的归并排序需要 1⁄2NlgN 至 NlgN 次比较。
 * <p>
 * 对于长度为 N 的任意数组，自顶向下的归并排序最多需要访问数组 6NlgN 次。
 * > 每次归并最多需要访问数组 6N 次(2N 次用来复制，2N 次用来将排好序的元素移动回去， 另外最多比较 2N 次)
 *
 * @author fxm
 * @date 2023/7/21 9:03 下午
 */
public class MergeSort implements Sort {
    /**
     * 辅助数组
     */
    private static int[] aux;

    @Override
    public void sort(int[] a) {
        aux = new int[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        // 左边排序
        sort(a, start, mid); // 优化1：可以针对子数组数量小于15时，使用插入排序
        // 右边排序
        sort(a, mid + 1, end);
        // 合并排序结果
        merge(a, start, mid, end); // 优化2：可以判断如果a[mid] <= a[mid + 1]就不进行merge操作
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
        new MergeSort().sort(a);
    }
}
