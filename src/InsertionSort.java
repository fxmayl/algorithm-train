/**
 * 插入排序
 * <p>
 * 与选择排序一样，当前索引左边的所有元素都是有序的，但它们的最终位置还不确定，为了给 更小的元素腾出空间，它们可能会被移动。
 * 但是当索引到达数组的右端时，数组排序就完成了。
 * 和选择排序不同的是，插入排序所需的时间取决于输入中元素的初始顺序。
 * 例如，对一个很大 且其中的元素已经有序(或接近有序)的数组进行排序将会比对随机顺序的数组或是逆序数组进行 排序要快得多。
 * <p>
 * 对于 1 到 N-1 之间的每一个 i，将 a[i] 与 a[0] 到 a[i-1] 中比它大的所有元素依次有序地交换。
 * 在索引 i 由左向右变化的过程中，它左侧的元素总是有序的，所以当 i 到达数组的右端时排序就完成了。
 *
 * @author fxm
 * @date 2023/7/20 9:36 上午
 */
public class InsertionSort implements Sort {
    @Override
    public void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            // 将 a[i] 插入到 a[i-1]、a[i-2]、a[i-3]...之中
            for (int j = i; j > 0 && Sort.less(a[j], a[j - 1]); j--) {
                Sort.exchange(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 5, 4, 9, 6};
        new InsertionSort().sort(a);
    }
}
