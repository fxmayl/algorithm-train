import org.junit.Test;

/**
 * @author : fxm
 * @program : algorithm-train
 * @description : 动态规划算法
 *  1、斐波那契算法不算是动态规划，存在重叠子问题，但是不是求最优子结构
 *  2、动态规划有求最优子结构的特点，从最优子结构中得到整体最优
 *  动态规划：如何列出正确的状态转移 方程
 *      1、状态   也就是原问题和子问题中变化的变量
 *      2、然后确定 dp 函数的定义
 *      3、然后确定「选择」并择优   也就是对于每个状态，可以做出什么选择改变当 前状态
 *      4、最后明确 base case  结束条件
 *      只要通过  状态转移方程写出暴力递归解
 *      明确「状态」 -> 定义 dp 数组/函数的含义 -> 明确「选择」-> 明确 base case。
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
