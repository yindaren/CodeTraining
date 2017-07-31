import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 2sum: 给一个整数数组，找到2个数使得他们的和等于一个给定的数 target。返回二元组（a，b），a < b，a，b为下标。注意这里下标的范围是 1 到 n，不是以 0 开头。
 * 3sum: 给一个整数数组，找到所有的和为给定整数数 target的三元组。返回所有三元组（a，b，c），a < b < c，a，b，c为实际值，且去除重复
 * nearest-3sum:给一个整数数组，找到3个数使得他们的和最接近一个给定的数 target。只需返回这三个数的和
 *
 *
 * 2-sum较简单，且要求返回下标，不能排序
 * 3-sum先对数组进行排序，方便处理。
 * 更多数之和都是类似的
 */
public class TwoAndThreeSum {
  /**
   * @param numbers : An array of Integer
   * @param target : target = numbers[index1] + numbers[index2]
   * @return : [index1 + 1, index2 + 1] (index1 < index2)
   */
  public int[] twoSum(int[] numbers, int target) {
    int[] index = new int[2];
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i =0; i < numbers.length; i++) {
      if (map.containsKey(target - numbers[i])) {
        index[0] = map.get(target - numbers[i]) + 1;
        index[1] = i + 1;
        break;
      }
      map.put(numbers[i], i);
    }
    return index;
  }

  public ArrayList<ArrayList<Integer>> threeSum(int[] numbers, int target) {
    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    int len = numbers.length;
    if (len < 3) {
      return list;
    }
    Arrays.sort(numbers);
    int last = numbers[0] - 1;
    for (int i = 0; i < len - 2; i++) {//先找第一个
      if (numbers[i] == last) {//有相同的只要找一次即可
        continue;
      } else {
        last = numbers[i];
      }
      for (int j = i + 1; j < len - 1; j++) {//再找第二个
        int k = target - numbers[i] - numbers[j];
        if (k < numbers[j]) {//已经大于k，不可能存在
          break;
        }
        for (int p = j + 1; p < len; p++) {//最后找第三个
          if(numbers[p] == k) {
            ArrayList<Integer> tmp = new ArrayList<>(Arrays.asList(numbers[i], numbers[j], numbers[p]));
            if (!list.contains(tmp)) {
              list.add(tmp);
            }
          } else if (numbers[p] > k) {//已经大于k，不可能存在
            break;
          }
        }
      }
    }
    return list;
  }

  public int nearsthreeSum(int[] numbers, int target) {
    Arrays.sort(numbers);
    int len = numbers.length;
    int last = numbers[0] - 1;
    int sum = numbers[0] + numbers[1] + numbers[2];
    for (int i = 0; i < len - 2; i++) {//先找第一个
        if (numbers[i] == last) {//有相同的只要找一次即可
          continue;
        } else {
          last = numbers[i];
        }
      for (int j = i + 1; j < len - 1; j++) {//再找第二个
        int k = target - numbers[i] - numbers[j];
        if (k < 0 && numbers[j] >=0 && numbers[i] + numbers[j] >= sum) {//剪枝
          break;
        }
        for (int p = j + 1; p < len; p++) {//最后找第三个
          if(numbers[p] == k) {
            return target;
          } else if (Math.abs(k - numbers[p] ) < Math.abs(sum - target)) {
            sum = numbers[i] + numbers[j] + numbers[p];
            if (sum > target) { //剪枝
              break;
            }
          }
        }
      }
    }
    return sum;
  }

}
