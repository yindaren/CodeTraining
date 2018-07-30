package util;

import java.util.Scanner;

public class utils {

  public static void printArray(int[] a) {
    printArray(a, 0, a.length);
  }

  public static void printArray(int[] a, int start, int end) {
    for(int i=start;i<end;i++) {
      System.out.print(a[i]);
      System.out.print(",");
    }
    System.out.print("\n");
  }

  public static void printArray(int[][] a) {
    int m = a.length;
    int n = a[0].length;
    printArray(a, 0, m, 0, n);
  }

  public static void printArray(int[][] a, int rowS, int rowEnd, int colS, int colE) {
    for(int i=rowS;i<rowEnd;i++) {
      for(int j=colS;j<colE;j++) {
        System.out.print(a[i][j]);
        System.out.print(",");
      }
      System.out.print("\n");
    }
  }

  public static void print(Object o) {
    System.out.print(o);
  }

  public static void println(Object o) {
    System.out.println(o);
  }

  public static Scanner getScanner() {
    return new Scanner(System.in);
  }
}
