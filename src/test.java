import java.util.ArrayList;
import java.util.List;

public class test {

  public List<Integer> spiralOrder(int[][] matrix) {
    // write your code here
    List<Integer> result = new ArrayList<>();
    int rows = matrix.length;
    if(rows == 0) return result;
    int columns = matrix[0].length;
    if(columns == 0) return result;
    int a=0,b=0,c=rows-1,d=columns-1;
    while(a<=c&&b<=d) {
      for(int i=b;i<=d;i++) {
        result.add(matrix[a][i]);
      }
      for(int i=a+1;i<=c;i++) {
        result.add(matrix[i][d]);
      }
      for(int i=d-1;i>=b;i--) {
        result.add(matrix[c][i]);
      }
      for(int i=c-1;i>a;i--) {
        result.add(matrix[i][b]);
      }
      a++;
      b++;
      c--;
      d--;
    }

    return result;
  }


  public static void printArray(int[][] a) {
    int m = a.length;
    int n = a[0].length;
    for(int i=0;i<m;i++) {
      for(int j=0;j<n;j++) {
        System.out.print(a[i][j]);
        System.out.print(",");
      }
      System.out.print("\n");
    }
  }

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


  static class Node {
    Node left, right;
  }



  public static void main(String[] args) {
    int[][] dis = {{0,2,3}, {2,0,1}, {3,1,0}};
    int[][] r = minDis(dis, 2);
    printArray(r);
  }
}
