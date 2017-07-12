import java.util.HashMap;

/**
 * 给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。
 * 你需要实现的函数twoSum需要返回这两个数的下标, 并且第一个下标小于第二个下标。
 * 注意这里下标的范围是 1 到 n，不是以 0 开头。
 * 这题较简单
 */
public class TwoSum {
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

  public static void main(String[] args) {

  }
}
