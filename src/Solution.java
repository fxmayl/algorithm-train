import org.junit.Test;

import java.util.*;

/**
 * @author fxm
 * @date 2023/3/3 3:27 下午
 */
public class Solution {

    @Test
    public void test1() {
        minimumRecolors("WBWW", 2);
    }

    @Test
    public void test2() {
        minNumberOfHours(5, 3, new int[]{1, 4}, new int[]{2, 5});
    }

    private int index = 0;

    @Test
    public void test3() {
        System.out.println(calculate("3 * (4 - 5 / 2) - 6"));
    }


    public int minimumRecolors(String blocks, int k) {
        int left = 0;
        int right = 0;
        int wNums = 0;
        int res = k + 1;
        int prevWIndex = -1;

        int len = blocks.length();
        while (right < len) {
            if ((right - left + 1) < k) {
                if (blocks.charAt(right) == 'W') {
                    wNums++;
                    prevWIndex = right;
                }
                right++;
                continue;
            }
            if (wNums == 0) {
                return 0;
            }
            res = Math.min(res, wNums);
            left = prevWIndex + 1;
            wNums = 0;
        }
        return res;
    }

    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int ans = 0;
        int sumEnmergy = 0;
        for (int i = 0; i < energy.length; i++) {
            sumEnmergy += energy[i];
        }
        if (sumEnmergy > initialEnergy) {
            ans = sumEnmergy - initialEnergy + 1;
        }
        int maxExperience = 0;
        int maxExperienceIndex = 0;
        int[] prefixSum = new int[experience.length];
        for (int i = 0; i < experience.length; i++) {
            if (experience[i] > maxExperience) {
                maxExperience = experience[i];
                maxExperienceIndex = i;
            }
            if (i > 0) {
                prefixSum[i] = prefixSum[i - 1] + experience[i];
            } else {
                prefixSum[i] = experience[i];
            }
        }
        if (maxExperienceIndex == 0) {
            if (initialExperience > maxExperience) {
                return ans;
            } else {
                return maxExperience - initialExperience + 1 + ans;
            }
        }
        if ((prefixSum[maxExperienceIndex - 1] + initialExperience) < maxExperience) {
            return ans + maxExperience - prefixSum[maxExperienceIndex - 1] - initialExperience + 1;
        }
        int maxE = 0;
        int maxI = 0;
        for (int i = 0; i < maxExperienceIndex; i++) {
            if (i != 0 && (prefixSum[i - 1] + initialExperience) < experience[i]) {
                maxE = Math.max(maxE, experience[i]);
                maxI = i;
            }
            if (i == 0 && initialExperience < experience[i]) {
                maxE = Math.max(maxE, experience[i]);
                maxI = i;
            }
        }
        if (maxI == 0) {
            return ans + maxE - initialExperience + 1;
        }
        return ans + maxE - prefixSum[maxI - 1] - initialExperience + 1;
    }

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int nums = 0;

        for (; index < s.length(); index++) {
            char c =  s.charAt(index);
            if (isDigit(c)) {
                nums = nums * 10 + (c - '0');
            }
            if (c == '(') {
                index = index + 1;
                nums = calculate(s);
            }
            if ((!isDigit(c) && c != ' ') || s.length() - 1 == index) {
                switch (sign) {
                    case '+':
                        stack.push(nums);
                        break;
                    case '-':
                        stack.push(-nums);
                        break;
                    case '*':
                        Integer integer = stack.pop();
                        stack.push(integer * nums);
                        break;
                    case '/':
                        Integer pop = stack.pop();
                        stack.push(pop / nums);
                        break;
                }
                sign = c;
                nums = 0;
            }
            if (c == ')') {
                break;
            }
        }
        int sum = 0;
        for (int i = 0; i < stack.size(); i++) {
            sum += stack.get(i);
        }
        return sum;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

}
