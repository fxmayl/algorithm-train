/**
 * 三向切分快速排序
 *
 * @author fxm
 * @date 2023/7/25 4:33 下午
 */
public class Quick3WaySort implements Sort {
    @Override
    public void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     *
     * 它从左到右遍历数组 一次，维护一个指针 lt 使得 a[start..lt-1] 中的元素都小于 v，
     * 一个指针 gt 使得 a[gt+1..end] 中 的元素都大于 v，
     * 一个指针 i 使得 a[lt..i-1] 中的元素都等于 v，a[i..gt] 中的元素都还未确定，
     *
     * 一开始 i 和 start 相等，我们使用 Comparable 接口(而非less())对 a[i] 进行三向比较来直接处理以下情况:
     * > a[i]小于v，将a[lt]和a[i]交换，将lt和i加一;
     * > a[i] 大于 v，将 a[gt] 和 a[i] 交换，将 gt 减一;
     * > a[i]等于v，将i加一。
     */
    public static void sort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int lt = start;
        int i = start + 1;
        int gt = end;
        int v = a[start];
        while (i <= gt) {
            if (a[i] < v) {
                Sort.exchange(a, i++, lt++);
            } else if (a[i] > v) {
                Sort.exchange(a, i, gt--);
            } else {
                i++;
            }
        } // 现在 a[start..lt-1] < v = a[lt..gt] < a[gt+1..end]成立
        sort(a, start, lt - 1);
        sort(a, gt + 1, end);
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 5, 4, 9, 6};
        new Quick3WaySort().sort(a);
    }
}
