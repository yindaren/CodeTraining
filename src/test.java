import util.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


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







  public static void main(String[] args) {
    Queue<Integer> a = new LinkedList<>();
    Stack<Integer> b = new Stack<>();
    int[][] dis = {{0,2,3}, {2,0,1}, {3,1,0}};
    //utils.printArray(null);
  }
}
