import util.utils;

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







  static class Node {
    Node left, right;
  }



  public static void main(String[] args) {
    int[] a = {1,2,3};
    "".contains("");
    int[][] dis = {{0,2,3}, {2,0,1}, {3,1,0}};
    //utils.printArray(null);
  }
}
