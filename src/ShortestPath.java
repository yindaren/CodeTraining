import util.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * 最短路径算法：dijkstra, floyd, bellman-ford
 */
public class ShortestPath {

  static private int findMin(int[] path, Set<Integer> rest) {
    int min = Integer.MAX_VALUE;
    int r = -1;
    for(int i: rest) {
      if(path[i] != -1 && path[i]<min) {
        r = i;
        min = path[i];
      }
    }
    return r;
  }


  /**
   * 每次找到一条最小路径，然后以此更新其他路径
   * 无法处理负环
   * o(n*n)
   */
  static void dijkstra(int[][] data, int source) {
    int n = data.length;
    int[] path = new int[n];
    Set<Integer> rest = new HashSet<>();
    for(int i=0;i<n;i++) {
      path[i]=data[source][i];
      rest.add(i);
    }
    rest.remove(source);
    while(rest.size() > 1) {
      int index = findMin(path, rest);
      int distance = path[index];
      rest.remove(index);
      for(int i: rest) {
        if(data[index][i] != -1 && (path[i] == -1 || distance+data[index][i] < path[i])) {
          path[i] = distance+data[index][i];
        }
      }
    }
    utils.printArray(path);
  }

  /**
   * 思想：用当前已经找到的最小边去松弛结果，找到更小的结果（当前路径中再加入中间节点）.
   * 松弛n-1轮，若第n轮还能松弛，则说明存在负环。因为n个节点n条边必然存在环，而存在环
   * 反而使得路径边短，那么这个环必然是负环
   * o(n*e)
   */
  static void bellman(int[][] data, int source) {

  }

  /**
   * 思想是动态规划. p[i][j][k]表示最小路径i->j中最大节点为k时的值。
   * P[i][j][0]即表示Eij的长度，点下标从1开始计数，递推式如下：
   * p[i][j][k] = min( p[i][j][k-1], p[i][k][k-1] + p[k][j][k-1] )
   * 故可根据k一层一层求解，详见以下实现
   */
  static void floyd(int[][] data) {
    int n = data.length;
    int[][] path = new int[n+1][n+1];
    for(int i=0;i<=n;i++) path[i] = new int[n+1];
    for(int i=0;i<n;i++) {
      for(int j=0;j<n;j++) {
        path[i+1][j+1] = data[i][j];
      }
    }
    for(int k=1;k<=n;k++) {
      for(int i=1;i<=n;i++) {
        for(int j=1;j<=n;j++) {
          if(path[i][k]!=-1 && path[k][j]!=-1 && (path[i][j] == -1 || path[i][k] + path[k][j] < path[i][j])) {
            path[i][j] = path[i][k] + path[k][j];
          }
        }
      }
    }
    utils.printArray(path, 1, n+1, 1, n+1);
  }

  public static void main(String[] args) {
    int[][] example = {{0, 1, 12, -1, -1, -1}, {4, 0, 9, 3, -1, -1},
                       {6, -1, 0, -1, 5, -1}, {-1, -1, 4, 0, 13, 15},
                       {-1, -1, -1, -1, 0, 4}, {-1, -1, -1, 3, -1, 0}};
    int i=-1;
    utils.print("Dijkstra:");
    while(i++<5)
      dijkstra(example, i);
    i=-1;
    while(i++<5)
      bellman(example, i);
    utils.print("Floyd:");
    floyd(example);
  }
}
