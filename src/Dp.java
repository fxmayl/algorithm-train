import org.junit.Test;

/**
 * @author fxm
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
            // 每种硬币数量是没有限制的
            // 要想凑成amount金额，则看 (amount - coin) 这个金额所需的硬币数量， 在这个基础之上加 1 个即可
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
