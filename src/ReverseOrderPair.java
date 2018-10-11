import java.util.Scanner;

/**
 * 剑指offer：寻找逆序对，利用归并排序的性质统计逆序对，复杂度O（nlogn）
 */
public class ReverseOrderPair {
  static int count = 0;
  static int[] a;
  static int[] b;

  public static void count(int first, int last) {
    if (first < last) {
      int mid = (first + last) / 2;
      count(first, mid);
      count(mid + 1, last);
      merge(first, mid, last);
    }
  }

  public static void merge(int first, int mid, int last) {
    int i = first, j = mid + 1;
    int m = mid, n = last;
    int k = 0;
    while (i <= m && j <= n) {
      if (a[i] > a[j]) {
        b[k++] = a[j++];
        count += mid - i + 1;
      } else {
        b[k++] = a[i++];
      }
    }

    while (i <= m) {
      b[k++] = a[i++];
    }          while (j <= n) {
      b[k++] = a[j++];
    }
    for (i = 0; i < k; i++)
      a[first + i] = b[i];
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    a = new int[n];
    b = new int[n];
    for(int i=0;i<n;i++) {
      a[i] = in.nextInt();
    }
    count(0, n-1);
    System.out.print(count);
  }
}
