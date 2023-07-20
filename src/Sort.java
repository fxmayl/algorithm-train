/**
 * 排序模版
 *
 * @author fangxiaoming
 * @date 2023/7/20 9:37 上午
 */
public interface Sort {
    static boolean less(int v, int w) {
        return v < w;
    }

    static void exchange(int[] a, int i, int j) {
        int v = a[j];
        a[j] = a[i];
        a[i] = v;
    }

    void sort(int[] a);
}
