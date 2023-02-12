import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fxm
 * @date 2023/2/12 11:33 上午
 * @description 滑动窗口
 */
public class SlidingWindow {
    @Test
    public void test1() {
        System.out.println(minCoverSubSeq("EBBANCF", "ABC"));
    }

    @Test
    public void test2() {
        System.out.println(checkIn("hellowtorld", "oow"));
    }

    /**
     * 最小覆盖字串
     * <p>
     * 在字符串s中找到包含t中全部字母的最短字串
     *
     * @return
     */
    private String minCoverSubSeq(String s, String t) {
        Map<Character, Integer> needs = new HashMap<>();
        char[] tChars = t.toCharArray();
        for (int i = 0; i < tChars.length; i++) {
            needs.put(tChars[i], needs.getOrDefault(tChars[i], 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();
        int right = 0;
        int left = 0;
        // 用于表示字符串t中字母的个数
        int valid = 0;
        // 表示最短的长度
        int len = Integer.MAX_VALUE;
        int start = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (needs.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                // 表示窗口中的字母 c 满足的条件
                if (window.get(c).equals(needs.get(c))) {
                    valid++;
                }
            }
            while (valid == needs.size()) {
                // 获取最小覆盖长度
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // 移动窗口左边
                char leftChar = s.charAt(left);
                left++;
                if (needs.containsKey(leftChar)) {
                    // 如果leftChar的数量与 t 中一致，则在缩短左边窗口的时候，valid就不满足，需要减去1
                    if (window.get(leftChar).equals(needs.get(leftChar))) {
                        valid--;
                    }
                    window.put(leftChar, window.get(leftChar) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }


    /**
     * 校验字符串s中是否包含t中的所有字母，且不含邮其他的字母
     *
     * @return
     */
    private boolean checkIn(String s, String t) {
        Map<Character, Integer> needs = new HashMap<>();
        char[] tChars = t.toCharArray();
        for (int i = 0; i < tChars.length; i++) {
            needs.put(tChars[i], needs.getOrDefault(tChars[i], 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();
        int right = 0;
        int left = 0;
        // 用于表示字符串t中字母的个数
        int valid = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (needs.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                // 表示窗口中的字母 c 满足的条件
                if (window.get(c).equals(needs.get(c))) {
                    valid++;
                }
            }
            // right - left >= t.length() 表示 窗口的大小始终只有 t.length() 这个大小
            // 因为 当valid 一直不合法时， 窗口左侧会一直缩小
            while (right - left >= t.length()) {
                // 判断是否找到了合法的
                if (valid == needs.size()) {
                    return true;
                }
                // 移动窗口左边
                char leftChar = s.charAt(left);
                left++;
                if (needs.containsKey(leftChar)) {
                    // 如果leftChar的数量与 t 中一致，则在缩短左边窗口的时候，valid就不满足，需要减去1
                    if (window.get(leftChar).equals(needs.get(leftChar))) {
                        valid--;
                    }
                    window.put(leftChar, window.get(leftChar) - 1);
                }
            }
        }
        return false;
    }
}
