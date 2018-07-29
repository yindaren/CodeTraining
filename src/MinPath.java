/**
 * 给定一个二维数组dis，dis[i][j]表示点i到点j的距离，给定路径数k, 求出所有点之间的最短路径
 * 若无k的限制，单源最短路径可用Dijkstra算法
 *
 * 如[0,1,3
 *   1,0,1, 给定路径数2, 0->0 =2(0->1->0), 0->1=4(0->2->1), 需要考虑重复的路径
 *   3,1,0]
 *
 *   思路： 动态规划, dp[i][j][k] = dp[p][j][k-1] (p!=i, 路径可重复)
 */

public class MinPath {
  public static int[][] minDis(int[][] dis, int n) {
    int m = dis.length;
    int[][] result = new int[m][m];
    int[][][] dp = new int[m][m][n];
    for(int i=0;i<m;i++) {
      dp[i] = new int[m][n];
      for(int j=0;j<m;j++) {
        dp[i][j] = new int[n];
        if(i!=j) {
          dp[i][j][0] = dis[i][j];
        }
      }
    }
    for(int k=1;k<n;k++) {
      for(int i=0;i<m;i++) {
        for(int j=i;j<m;j++) {
          int min = Integer.MAX_VALUE;
          for(int p=0;p<m;p++) {
            if(p!=i && dp[p][j][k-1]!=0) {
              min = Math.min(min, dis[i][p] + dp[p][j][k-1]);
            }
          }
          dp[i][j][k] = min;
          dp[j][i][k] = min;
        }
      }
    }
    for(int i=0;i<m;i++) {
      for(int j=0;j<m;j++) {
        result[i][j] = dp[i][j][n-1];
      }
    }
    return result;
  }
}
