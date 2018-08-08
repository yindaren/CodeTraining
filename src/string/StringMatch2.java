package string;

import util.utils;

/**
 * 字符串匹配，与1不同的是，子串只需按顺序包含目标串字符即可。如：abcde中的bcd包含bd
 */
public class StringMatch2 {
  public static int count(String base, String target) {
    if(base==null || base.length() == 0 || target==null || target.length()==0) return -1;
    int m = base.length();
    int n = target.length();
    int[][] dp = new int[m][];
    dp[0] = new int[n];
    if(base.charAt(0) == target.charAt(0)) dp[0][0]=1;
    for(int i=1;i<m;i++) {
      dp[i] = new int[n];
      for(int j=0; j<n;j++) {
        if(j<=i) {
          if(base.charAt(i) == target.charAt(j)) {
            if(j==0) dp[i][j] = i+1 + dp[i-1][j];
            else dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
          }
          else {
            dp[i][j] = dp[i-1][j];
          }
        }
      }
    }
    return dp[m-1][n-1];
  }

  public static void main(String[] args) {
    utils.println(StringMatch2.count("asb", "s"));
  }
}
