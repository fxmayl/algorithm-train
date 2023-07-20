

/**
 * 选择排序
 * 首先，找到数组中最小的那个元素，
 * 其次，将它和数组的第 一个元素交换位置(如果第一个元素就是最小元素那么它就和自己交换)。
 * 再次，在剩下的元素中 找到最小的元素，将它与数组的第二个元素交换位置。如此往复，直到将整个数组排序
 *
 * @author fxm
 * @date 2023/7/20 9:26 上午
 */
public class SelectionSort {
    private static boolean less(int v, int w) {
        return v < w;
    }

    private static void exchange(int[] a, int i, int j) {
        int v = a[j];
        a[j] = a[i];
        a[i] = v;
    }

    public static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exchange(a, min, i);
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 5, 4, 9, 6};
        sort(a);
    }
}
