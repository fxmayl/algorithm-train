/**
 * 快速排序
 *
 * 快速排序流行的原因是它实现简单、 适用于各种不同的输入数据且在一般应用中比其他排序算法都要快得多。
 * 快速排序引人注目的特点包 括它是原地排序(只需要一个很小的辅助栈)，且将长度为 N 的数组排序所需的时间和 NlgN 成正比。
 * 另外，快速排序的内循环比大多数排序算法都要短小，这意味着它无论是在理论上还是在实际中都要更快。
 * 它的主要缺点是非常脆弱，在实现时要非常小心才能避免低劣的性能。已经有无数例子显示许多种错误都能致使它在实际中的性能只有平方级别。
 *
 * 快速排序是一种分治的排序算法。它将一个数组分成两个子数组，将两部分独立地排序。
 * 快速排序和归并排序是互补的:
 *      归并排序将数组分成两个子数组分别排序，并将有序的子数组归并以将整个数组排序;
 *      而快速排序将数组排序的方式则是当两个子数组都有序时整个数组也就自然有序了。
 * 在第一种情况(归并排序)中，递归调用发生在处理整个数组之前;
 * 在第二种情况(快速排序)中，递归调用发生在处理整个数组之后。
 * 在归并排序中，一个数组被等分为两半;
 * 在快速排序中，切分(partition)的位置取决于数组的内容。
 *
 * 将长度为 N 的无重复数组排序，快速排序平均需要 ~2NlnN 次比较(以及 1/6 的交换)。
 *
 * CN~2(N+1)(1/3+1/4+...+1/(N+1))
 * 括号内的量是曲线 2/x 下从 3 到 N 的离散近似面积加一，积分得到 CN~2NlnN。注意到 2NlnN ≈ 1.39NlgN，也就是说平均比较次数只比最好情况多 39%。
 *
 * 快速排序最多需要约 (N ^ 2)/2 次比较，但随机打乱数组能够预防这种情况。
 *
 * @author fxm
 * @date 2023/7/24 9:28 上午
 */
public class QuickSort implements Sort {
    @Override
    public void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * 该方法的关键在于切分，这个过程使得数组满足下面三个条件:
     * > 对于某个 j，a[j] 已经排定;
     * > a[start] 到 a[j-1] 中的所有元素都不大于 a[j];
     * > a[j+1] 到 a[end] 中的所有元素都不小于 a[j]。
     *
     * 快速排序递归地将子数组 a[start..end] 排序，先用 partition() 方法将 a[j] 放到一个合适位置，然
     * 后再用递归调用将其他位置的元素排序。
     */
    private static void sort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int partition = partition(a, start, end);
        sort(a, start, partition -1); // 将左半部分a[start .. j-1]排序
        sort(a, partition + 1, end); // 将右半部分a[j+1 .. end]排序
    }

    private static int partition(int[] a, int start, int end) {
        // 将数组切分为a[start..i-1], a[i], a[i+1..end]
        int i = start; // 左右扫描指针
        int j = end + 1;
        int partition = a[start]; // 切分元素
        while (true) {
            // 扫描左右，检查扫描是否结束并交换元素
            while (Sort.less(a[++i], partition)) {
                if (i == end) {
                    break;
                }
            }
            while (Sort.less(partition, a[--j])) {
                if (j == start) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            Sort.exchange(a, i, j);
        }
        // 将partition = a[j]放入正确的位置
        Sort.exchange(a, start, j);
        return j; // a[start..j-1] <= a[j] <= a[j+1..end] 达成
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 5, 4, 9, 6};
        new QuickSort().sort(a);
    }
}
