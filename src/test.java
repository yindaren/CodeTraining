import treeAndGraph.Node;
import util.utils;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
=======
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
>>>>>>> 13d37f0d1c192ddde566920f3ab1d845f6d7178b
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


  public static boolean isInterleave(String s1, String s2, String s3) {
    // write your code here
    if(s1==null || s2 == null || s3 == null) return false;
    int n = s1.length();
    int m = s2.length();
    int t = s3.length();
    if(t != m+n) return false;
    StringBuilder sb = new StringBuilder();
    int p = 0;
    for(int i=0;i<n;i++) {
      while(p < t && s1.charAt(i) != s3.charAt(p)) {
        sb.append(s3.charAt(p++));
      }
      if(p==t) return false;
      else p++;
    }
    if(p<t) sb.append(s3.substring(p));
    if(sb.toString().equals(s2)) return true;
    else return false;

  }
   static class Node {
      final int value;
      Node left;
      Node right;

      Node(int v) {
        value = v;
      }
   }

   public static void a(String a) {
      a+="s";
   }

  public static void main(String[] args) {

  }
}
