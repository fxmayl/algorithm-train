import org.junit.Test;

/**
 * @author fangxiaoming
 * @date 2023/2/11 4:50 下午
 * 动态规划
 */
public class Dp {
    @Test
    public void test1() {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
    }

    private int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subproblem = coinChange(coins, amount - coin);
            if (subproblem == -1) {
                continue;
            }
            // 选择了一个coin
            res = Math.min(res, subproblem + 1);
        }
        if (res != Integer.MAX_VALUE) {
            return res;
        } else {
            return -1;
        }
    }
}
