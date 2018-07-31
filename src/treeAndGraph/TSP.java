package treeAndGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 旅行家问题
 * 起点s（0,0），其他地点均在网格上，只能上下左右走，输入第一行点数n，第二行（x1,y1）,（x2,y2）.....
 *
 * 思路：动态规划， dp关系：[s, [1,2,3,...,s]] = min(dis[i, s]+[i, [1,2,3...n](去掉s)])， i=1,2,....,n
 */
public class TSP {
  static int dis(int[][] p, int a, int b) {
    return Math.abs(p[a][0] - p[b][0]) + Math.abs(p[a][1] - p[b][1]);
  }

  static int visit(int end, Set<Integer> visited, int[][] p, Map<Integer, Map<Set<Integer>, Integer>> dp) {
    int min = Integer.MAX_VALUE;
    Set<Integer> set = new HashSet<>(visited);
    set.remove(end);
    for(int i: visited) {
      if(i != end) {
        if(dp.get(i).containsKey(set)) {
          min = Math.min(dis(p, i, end) + dp.get(i).get(set), min);
        }
        else {
          int tmpMin = visit(i, set, p, dp);
          min = Math.min(dis(p, i, end) + tmpMin, min);
        }
      }
    }
    dp.get(end).put(visited, min);
    return min;
  }

  static int calculate(String[] locations) {
    int num = locations.length;
    int[][] p = new int[num+1][2];

    p[0] = new int[2];
    p[0][0] = p[0][1] = 0;
    for(int i=1;i<=num;i++) {
      p[i] = new int[2];
      String[] pp = locations[i].split(",");
      p[i][0] = Integer.parseInt(pp[0]);
      p[i][1] = Integer.parseInt(pp[1]);
    }

    Set<Integer> initialSet = new HashSet<>();
    Map<Integer, Map<Set<Integer>, Integer>> dp = new HashMap<>();
    for(int i = 0;i<=num;i++) {
      Map<Set<Integer>, Integer> map = new HashMap<>();
      Set<Integer> set = new HashSet<>();
      set.add(i);
      map.put(set, dis(p, i, 0));
      dp.put(i, map);

      initialSet.add(i);
    }

    return visit(0, initialSet, p, dp);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int num = Integer.parseInt(scanner.nextLine().trim());
    int index = 0;
    String[] locations = new String[num];
    while(num-- > 0){
      locations[index++] = scanner.nextLine();
    }

    System.out.println(calculate(locations));
  }
}
