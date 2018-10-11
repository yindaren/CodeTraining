package permutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个数字列表（假定无重复值），返回其所有可能的排列,用递归解决
 *
 * 如对于[1,2,3],返回
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class FullArrangement {
    /**
     * 递归解决，tmp数组保存当前情况前n个已选择的数
     * @param list
     * @param nums
     * @param tmp
     * @param n
     */
    public void help(List<List<Integer>> list, int[] nums, int[] tmp, int n) {
        int len = nums.length;
        if (n == len) {
            List<Integer> tmpList = new ArrayList<Integer>();
            for (int i = 0;i < len; i++) {
                tmpList.add(tmp[i]);
            }
            list.add(tmpList);
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(tmp[i]);
        }
        for (int i = 0; i < len; i++) {
            if (!set.contains(nums[i])) {
                tmp[n] = nums[i];
                help(list, nums, tmp, n + 1);
            }
        }
    }

    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> list = new ArrayList<>();
        int[] tmp = new int[nums.length];
        help(list, nums, tmp, 0);
        return list;
    }
}
