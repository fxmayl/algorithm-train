import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : fxm
 * @program : algorithm-train
 * @description : 回溯算法  DFS
 * 解决一个回溯问题，实际上就是一个决 策树的遍历过程
 *      1、路径:也就是已经做出的选择
 *      2、选择列表:也就是你当前可以做的选择
 *      3、结束条件:也就是到达决策树底层，无法再做选择的条件
 *  回溯算法框架：
 *          result = []
 *          def backtrack(路径, 选择列表):
 *              if 满足结束条件:
 *                  result.add(路径)
 *                  return
 *              for 选择 in 选择列表:
 *                  做选择  将该选择再加入选择列表  路径.add(选择)
 *                  backtrack(路径, 选择列表)
 *                  撤销选择 将该选择从选择列表移除  路径.remove(选择)
 * @date : 2021-01-14 21:05
 **/
public class BackTrack {

    List<List<Integer>> res = new ArrayList<>();

    @Test
    public void test1() {
        LinkedList<Integer> track = new LinkedList<>();
        int[] nums = new int[]{1, 2, 3};
        backtrack(nums, track);
        System.out.println(res);
    }

    private void backtrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, track);
            track.removeLast();
        }
    }

}
