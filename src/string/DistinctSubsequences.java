package string;

/**
 * 给出字符串S和字符串T，计算S的不同的子序列中T出现的个数。
 * 子序列字符串是原始字符串通过删除一些(或零个)产生的一个新的字符串，并且对剩下的字符的相对位置没有影响。
 * (比如，“ACE”是“ABCDE”的子序列字符串,而“AEC”不是)。
 */
public class DistinctSubsequences {
  /**
   * 动态规划：dp[m][n] 表示s[0:m]中包含t[0,n]（包含m,n）的子串个数
   * dp[0][i]皆为0，dp[i][0]均取1，因为s必定包含空字符串“”
   * 当s[m]=t[n]时，dp[m][n] = dp[m-1][n-1] + dp[m-1][n], 否则dp[m][n] = dp[m-1][n]
   */
  public int numDistinct(String S, String T) {
    // write your code here
    if(S == null || T==null) return 0;
    if(T.length() == 0) return 1;

    int m = S.length(), n = T.length();
    if(m<n) return 0;

    int i,j=0;
    int[] dp = new int[n+1]; // 此处做了优化，只需要保留一行的数据
    dp[0] = 1;
    if(S.charAt(0) == T.charAt(0)) dp[1] = 1;
    for(i=1;i<m;i++) {
      for(j=n;j>=1;j--) { //必须反着计算，dp[j]需要dp[j-1]
        if(S.charAt(i) == T.charAt(j-1)) { //否则保持原样
          dp[j] += dp[j-1];
        }
      }
    }
    return dp[n];
  }
}
