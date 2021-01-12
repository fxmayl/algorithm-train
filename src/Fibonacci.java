import org.junit.Test;

/**
 * @author : fangxiaoming
 * @program : algorithm-train
 * @description : TODO
 * @date : 2021-01-12 19:48
 **/
public class Fibonacci {
    @Test
    public void test1() {
        System.out.println(fib1(6));
    }

    public int fib1(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib1(n - 1) + fib1(n - 2);
    }
}
