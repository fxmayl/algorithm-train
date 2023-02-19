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

    List<List<Integer>> result = new ArrayList<>();

    @Test
    public void test1() {
        LinkedList<Integer> track = new LinkedList<>();
        int[] nums = new int[]{1, 2, 3};
        backtrack(nums, track);
        System.out.println(result);
    }

    double res = 0.0;
    int loopCount = 0;
    @Test
    public void test2() {
        int[][] classes = {{684,883},{254,259},{66,797},{699,987},{458,828},{441,563},{257,555},{450,872},{465,551},{12,406},{347,857},{176,265},{25,498},{662,813},{427,956},{585,1000},{20,64},{364,709},{142,594},{129,608},{142,266},{284,849},{408,578},{177,411},{92,628},{240,498},{8,182},{325,542},{513,915},{665,943},{449,953},{655,703},{232,749},{245,321},{507,704},{491,980},{231,730},{346,423},{574,626},{746,929},{670,940},{282,996},{225,662},{50,944},{74,782},{524,661},{378,899},{164,524},{785,812},{209,905},{306,320},{307,710},{566,870},{170,381},{719,719},{476,755},{88,609},{127,877},{621,919},{527,984},{387,585},{160,181},{257,437},{223,965},{584,737},{776,802},{54,507},{404,698},{653,735},{357,394},{528,866},{169,558},{42,748},{93,537},{262,828},{104,644},{274,755},{86,935},{983,999},{143,993},{632,795},{863,991},{676,704},{84,718},{456,872},{247,947},{872,995},{392,963},{822,926},{407,444},{169,932},{334,449},{130,638},{500,931},{218,983}};
        int extraStudents = 5976;
//        System.out.println(classes.length);
        maxAverageRatio(classes, extraStudents, 0, 0.0);
        System.out.println(res);
    }

    private void backtrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            result.add(new LinkedList<>(track));
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

    public void maxAverageRatio(int[][] classes, int rest, int i, double averageRatio) {
        int len = classes.length;
        loopCount++;
        System.out.println(loopCount);

        if (i == len - 1 || rest == 0) {
            averageRatio += (classes[i][0] + rest) * 1.0 / (classes[i][1] + rest);
            for (int a = i + 1; a <= len - 1; a++) {
                averageRatio += (classes[a][0] * 1.0) / classes[a][1];
            }

            res = Math.max(averageRatio / len, res);
            return;
        }
        for (int a = 0; a <= rest; a++) {
            double ratio = (classes[i][0] + a) * 1.0 / (classes[i][1] + a) + averageRatio;
            maxAverageRatio(classes, rest - a, i + 1, ratio);
        }
    }

}
