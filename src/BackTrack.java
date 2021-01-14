import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : fangxiaoming
 * @program : algorithm-train
 * @description : TODO
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
