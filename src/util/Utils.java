package util;

import java.util.Scanner;

public class Utils {

  public static void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  /**
   * 快排一部分，以nums[base]为基准，将数组分成两部分
   *
   * @param base 基准值的下标
   * @return 最终基准数据在数组中的位置下标
   */
  public static int quickSortPartition(int[] nums, int base, int start, int end) {
    int tmp = nums[base];
    swap(nums, base, end);
    int p1= start-1; // p1指向【小于】-【大于】之间的分界线
    for(int i= start;i<end;i++) {
      if(nums[i]<tmp) {
        p1++;
        if(p1!=i) { //p1=i说明之前所有的都是小于tmp的，不需要移动，只有中间出现大于的数后，才需要移动
          swap(nums, i, p1);
        }
      }
    }
    p1++;
    swap(nums, p1, end);
    return p1;
  }

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
