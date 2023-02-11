import org.junit.Test;

/**
 * @author : fxm
 * @program : algorithm-train
 * @description : 二分查找详解
 * 查找框架：
 *       int binarySearch(int[] nums, int target) {
 *          int left = 0, right = ...;
 *          while(...) {
 *              int mid = left + (right - left) / 2;
 *              if (nums[mid] == target) {
 *                  ...
 *              } else if (nums[mid] < target) {
 *                  left = ...
 *              } else if (nums[mid] > target) {
 *                  right = ...
 *              }
 *          }
 *          return ...;
 *      }
 *
 *     分析二分查找的一个技巧是:不要出现 else，而是把所有情况用 else if 写清 楚，这样可以清楚地展现所有细节
 * @date : 2021-01-17 11:05
 **/
public class BinarySearch {

    @Test
    public void test1() {
        int i = binarySearch(new int[]{1, 2, 2, 2, 3}, 2);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = leftBound1(new int[]{1, 1, 1, 1, 2, 2, 2, 3}, 2);
        System.out.println(i);
    }

    @Test
    public void test3() {
        int i = leftBound2(new int[]{1, 1, 1, 1, 1, 2, 2, 2, 3}, 3);
        System.out.println(i);
    }

    @Test
    public void test4() {
        int i = rightBound1(new int[]{0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2}, 0);
        System.out.println(i);
    }

    @Test
    public void test5() {
        int i = rightBound2(new int[]{0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2}, 0);
        System.out.println(i);
    }

    @Test
    public void test6() {
        int i = rightBound3(new int[]{0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2}, 0);
        System.out.println(i);
    }


    /**
     * 寻找一个数(基本的二分搜索)
     *
     * @param nums
     * @param target
     * @return
     */
    int binarySearch(int[] nums, int target) {
        if (nums.length <= 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        // 使用区间[left, right], 跳出while循环条件  left == right + 1
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 寻找左侧边界的二分搜索  使用区间[left, right)
     *
     * @param nums
     * @param target
     * @return
     */
    int leftBound1(int[] nums, int target) {
        if (nums.length <= 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length;
        // 使用区间[left, right), 跳出循环的条件是  left == right
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                // 找到 target 时不要立即返回，而是缩小「搜索区间」的上界
                //right ，在区间 [left, mid) 中继续搜索，即不断向左收缩，达到锁定左
                //侧边界的目的
                right = mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 寻找左侧边界的二分搜索  使用区间[left, right]
     * @param nums
     * @param target
     * @return
     */
    int leftBound2(int[] nums, int target) {
        if (nums.length <= 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        // 使用区间[left, right], 跳出循环的条件是  left == right + 1
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                // 找到 target 时不要立即返回，而是缩小「搜索区间」的上界
                //right ，在区间 [left, mid) 中继续搜索，即不断向左收缩，达到锁定左
                //侧边界的目的
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 寻找右侧边界的二分查找  使用区间[left, right)
     * @param nums
     * @param target
     * @return
     */
    int rightBound1(int[] nums, int target) {
        if (nums.length <= 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length;
        // 使用区间[left, right), 跳出循环的条件是  left == right
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                // 找到 target 时不要立即返回，而是缩小「搜索区间」的下界
                //left ，在区间 [left, right) 中继续搜索，即不断向右收缩，达到锁定右
                //侧边界的目的
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        if (left > nums.length || left <= 0 || nums[left - 1] != target) {
            return -1;
        }

        return left - 1;
    }


    /**
     * 寻找右侧边界的二分查找  使用区间[left, right]
     * @param nums
     * @param target
     * @return
     */
    int rightBound2(int[] nums, int target) {
        if (nums.length <= 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        // 使用区间[left, right), 跳出循环的条件是  left == right + 1
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                // 找到 target 时不要立即返回，而是缩小「搜索区间」的下界
                //left ，在区间 [left, right] 中继续搜索，即不断向右收缩，达到锁定右
                //侧边界的目的
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        if (left > nums.length || left <= 0 || nums[left - 1] != target) {
            return -1;
        }

        return left - 1;
    }

    int rightBound3(int[] nums, int target) {
        if (nums.length <= 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        // 使用区间[left, right), 跳出循环的条件是  left == right + 1
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 找到 target 时判断是否是最后一个元素或者下一个元素是否等于target
                // 是最后一个元素则直接返回，否则接着判断下一个元素是否等于target， 不是则直接返回
                // 否则将left设置为mid + 1
                if (mid == nums.length - 1 || nums[mid + 1] != target) {
                    return mid;
                }
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        return -1;
    }
}
