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
        System.out.println(fib1(9));
    }

    @Test
    public void test2() {
        System.out.println(fib2(9));
    }

    @Test
    public void test3() {
        System.out.println(fib3(9));
    }

    /**
     * 暴力求解
     *
     * @param n
     * @return
     */
    public int fib1(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        System.out.println("计算第" + n + "个");
        return fib1(n - 1) + fib1(n - 2);
    }

    /**
     * 备忘录
     *
     * @param n
     * @return
     */
    public int fib2(int n) {
        if (n < 1) {
            return 0;
        }
        // 备忘录全初始化为 0, 记录已经计算过的值
        int[] memo = new int[n + 1];
        return helper(memo, n);
    }

    public int helper(int[] memo, int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        // 已经计算过
        if (memo[n] != 0) {
            return memo[n];
        }
        System.out.println("计算第" + n + "个");
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
    }

    /**
     * dp 数组的迭代解法  DP table
     * @param n
     * @return
     */
    public int fib3(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
