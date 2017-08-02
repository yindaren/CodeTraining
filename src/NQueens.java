import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * n皇后问题是将n个皇后放置在n*n的棋盘上，皇后彼此之间不能相互攻击。
 * 给定一个整数n，返回所有不同的n皇后问题的解决方案。
 * 每个解决方案包含一个明确的n皇后放置布局，其中“Q”和“.”分别表示一个女王和一个空位置。
 *
 * 对于4皇后问题存在两种解决的方案：
 * [
 * [".Q..", // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * ["..Q.", // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 */
public class NQueens {

  /**
   * 辅助递归函数
   * @param list 保存结果
   * @param flag 保存当前递归之前的皇后摆放位置
   * @param n 皇后数量
   * @param num 当前已摆放皇后的数量
   */
  public void help(ArrayList<ArrayList<String>> list, int[] flag, int n, int num) {
    if (num == n) {//结束摆放，处理成相应的形式
      ArrayList<String> tmp = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        StringBuffer buf = new StringBuffer();
        int pos = flag[i];
        while (pos-- > 0) {
          buf.append(".");
        }
        buf.append("Q");
        pos = n - flag[i];
        while (--pos > 0) {
          buf.append(".");
        }
        tmp.add(buf.toString());
      }
      list.add(tmp);
      return;
    }
    Set<Integer> set = new HashSet<>();
    for (int j = num - 1; j >= 0; j--) {//获取不能存放的位置
      int m = flag[j];
      int dis = num - j;
      set.add(m);//列不能放
      set.add(m + dis);//斜线不能放
      set.add(m - dis);//斜线不能放，注意有可能会超过0到n的范围，但不影响程序
    }
    for (int i = 0; i < n; i++) {
      if (!set.contains(i)) {
        flag[num] = i;//不必担心恢复，因为每次递归都会设置相应的值
        help(list, flag, n, num + 1);
      }
    }
  }

  /**
   * Get all distinct N-Queen solutions
   * @param n: The number of queens
   * @return: All distinct solutions
   * For example, A string '...Q' shows a queen on forth position
   */
  ArrayList<ArrayList<String>> solveNQueens(int n) {
    ArrayList<ArrayList<String>> list = new ArrayList<>();
    //用数组flag来保存摆放第m个皇后之前的皇后的摆放位置，在递归时不需要担心破坏其他情况。
    //若用二维数组来保存可用的位置
    // TODO(yll) 可以尝试用位图法来实现 参考http://blog.csdn.net/hackbuteer1/article/details/6657109
    int[] flag = new int[n];
    help(list, flag, n, 0);
    return list;
  }

  /**
   * 递归辅助
   * @return 某一轮递归下的所有可能种类
   */
  public int helpTwo(int[] flag, int n, int num) {
    if (num == n) {//结束摆放，处理成相应的形式
      return 1;
    }
    Set<Integer> set = new HashSet<>();
    for (int j = num - 1; j >= 0; j--) {//获取不能存放的位置
      int m = flag[j];
      int dis = num - j;
      set.add(m);//列
      set.add(m + dis);//斜线
      set.add(m - dis);//斜线
    }
    int count = 0;
    for (int i = 0; i < n; i++) {
      if (!set.contains(i)) {
        flag[num] = i;//不必担心恢复，因为每次递归都会设置相应的值
        count += helpTwo(flag, n, num + 1);
      }
    }
    return count;
  }
  /**
   * 只需返回Nqueens问题可能的排列数
   */
  public int totalNQueens(int n) {
    //write your code here
    int[] flag = new int[n];
    return helpTwo(flag, n, 0);
  }
}
