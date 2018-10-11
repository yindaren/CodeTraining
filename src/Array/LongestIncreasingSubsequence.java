package Array;

/**
 * 最长递增子序列
 */
public class LongestIncreasingSubsequence {
  public static int LIS(int[] array) {
    //保存以i结尾的最长序列，递归求到n即可
    int[] lisLength=new int[array.length];

    for (int i = 0; i < array.length; i++) {
      lisLength[i]=1;
    }

    int max=1;

    for (int i = 1; i < array.length; i++) {
      for (int j = 0; j <i; j++) {

        // <= 非严格递增
        if (array[j]<=array[i]&&(lisLength[j]+1)>lisLength[i]) {
          lisLength[i]=lisLength[j]+1;
        }

        if (max<lisLength[i]) {
          max=lisLength[i];
        }
      }
    }
    return max;
  }
}
