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

    @Test
    public void test2() {
        System.out.println(isMatch("zaaab", 0, ".a*b", 0));
        System.out.println(isMatch("cb", 0, ".a*b", 0));
        System.out.println(isMatch("amnb", 0, "a..b", 0));
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

    // 正则表达式匹配
    // p 为模式串 只包含 . 和 *
    private boolean isMatch(String s, int i, String p, int j) {
        int sLen = s.length();
        int pLen = p.length();
        // 模式串已经结束，直接判断字符串是否已经结束
        if (j == pLen) {
            return i == sLen;
        }
        // 字符串匹配完了，就要判断模式串是否是 一个字符一个*这种情况
        if (i == sLen) {
            if ((pLen - j) % 2 == 1) {
                return false;
            }
            for (int a = j; a < pLen - 1; a += 2) {
                if (s.charAt(a + 1) != '*') {
                    return false;
                }
            }
            return true;
        }
        // 走动态规划进行匹配
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            // 模式串的下一个为 * ，进行0个或者多个匹配
            if (j < pLen - 1 && p.charAt(j + 1) == '*') {
                return isMatch(s, i, p, j + 2) // 匹配 0个，可以理解为 s 中与 p中的 字符不相等
                        // 匹配多个，可以理解为 s 与 p对应的字符相等，然后p还尝试接着匹配 s.charAt(i) == p.charAt(j),万一s.charAt(i + 1) == p.charAt(j)
                        || isMatch(s, i + 1, p, j);
            } else {
                return isMatch(s, i + 1, p, j + 1); // 直接两个字符就匹配了，s 和 p都进入下一个
            }
        } else {
            if (j < pLen - 1 && p.charAt(j + 1) == '*') {
                return isMatch(s, i, p, j + 2);// 匹配 0个，可以理解为 s 中与 p中的 字符不想等
            } else {
                return false;// 两个不想等，也不包含 * 就直接返回不想等
            }
        }
    }
}
