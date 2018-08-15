package string;

import util.utils;

/**
 * 字符串匹配算法。包括暴力法（BF）,(滚动哈系法)RK，KMP，BM，SUNDAY
 *
 * 注意：一下实现均省略类边界检查。
 *
 */
public class StringMatch {
  /**
   * O(m*n).若patt所有字符不同，则复杂度可以压缩到O（m）
   */
  static int bf(String str, String patt) {
    int m = str.length(), n = patt.length();
    for(int i=0;i<=m-n;i++) {
      int tmp = i;
      int j=0;
      for(;j<n;j++) {
        if(str.charAt(tmp++) != patt.charAt(j)) {
          break;
        }
      }
      if(j==n) return i;
    }
    return -1;
  }

  /**
   * RK 辅助hash方法，使用ascii码,当作一个128位数，不会出现hash碰撞
   */
  static private long getHash(String str) {
    // todo: 这里结果可能溢出
    long result = 0;
    for(char c: str.toCharArray()) {
      result = result * 128 + (int)c;
    }
    return result;
  }

  /**
   * 利用hash值来比较字符串是否相同，减少对patt串的遍历。
   * hash可能碰撞，需要遍历模式串：O（mn）
   */
  static int rk(String str, String patt) {
    int m = str.length(), n = patt.length();
    long pHash = getHash(patt);
    long tmpHash = -1;
    for(int i=0;i<=m-n;i++) {
      if(i==0) {
        tmpHash = getHash(str.substring(0, n));
      }
      else {
        tmpHash = (tmpHash - (long)str.charAt(i-1) * (long)Math.pow(128,n-1)) * 128 + (long)str.charAt(i+n-1);
      }
      if(tmpHash == pHash) return i;
    }
    return -1;
  }

  /**
   * 基本思路：abcabd最后的d不匹配，而根据abcab的对称可以直接从后面的ab开始匹配
   * 与暴力解法比较增大了后移的位数。最佳O（m），最差O（mn）
   */
  static int kmp(String source, String target) {
    int m = source.length(), n = target.length();
    int[] next = new int[n];
    for(int i=1;i<n;i++) {
      String tmp = target.substring(0,i+1);
      for(int j = i;j>0;j--) {
        if(tmp.substring(0,j).equals(tmp.substring(i-j+1,i+1))) {
          // next[i] 代表最大公共前后缀的长度，无则取0
          next[i] = j;
        }
      }
    }
    int i=0, j=0;
    while(i<m) {
      if(source.charAt(i) == target.charAt(j)) {
        if(j==n-1) return i-n+1;
        i++;
        j++;
      }
      else {
        if(j==0) i++; // 注意此处，若第一位就不可匹配，则将i前移
        j = next[j];
      }
    }
    return -1;
  }

  static int findChar(char t, String patt, int end) {
    for(int i=end;i>=0;i--) {
      if(patt.charAt(i) == t) return i;
    }
    return -1;
  }

  /**
   * 从后向前匹配，匹配失败则去找最后一位在patt中的位置（最右的）并进行相应的移动
   * 理解：既然不匹配，那么最后一位有可能在匹配的串中（也可能不在，此时可以移动整个串的距离），
   * 那么它必然与patt中某一位（除去patt最后一位）对齐，移动相应距离再进行匹配。
   * 最佳O（m），最差O（mn）
   */
  static int bm(String source, String target) {
    int m = source.length(), n = target.length();
    int i=n-1, j=n-1;
    while(i<m) {
      int tmp = i, p = j;
      for(;p>=0;p--) {
        if(source.charAt(tmp--) != target.charAt(p)) {
          // 找到最右边匹配的下标（不包含最后一位，故为p-1）
          int index = findChar(source.charAt(i), target, j-1);
          i += n-index-1;
          break;
        }
      }
      if(p<0) return i-j;
    }
    return -1;
  }

  /**
   * 从头开始匹配。若不匹配，那么类似bm，不过是用最后一位的后一位进行匹配,且可以包括patt最后一位。
   *
   * 最佳O（m），最差O（mn）
   */
  static int sunday(String source, String target) {
    int m = source.length(), n = target.length();
    int i=0;
    while(i<=m-n) {
      int tmp = i, p = 0;
      for(;p<n;p++) {
        if(source.charAt(tmp++) != target.charAt(p)) {
          // 找到最右边匹配的下标（不包含最后一位，故为p-1）
          if(i==m-n) return -1;
          int index = findChar(source.charAt(i+n), target, n-1);
          i += n-index;
          break;
        }
      }
      if(p==n) return i;
    }
    return -1;
  }

  public static void main(String[] args) {
    String str = "tartargetlintcodelintcdejsahriuiwuiurasflhsajfhwahreuwreuwllhfasjflhajshriuwheujwlhadfhsaljfhjahwjehjwhiuehyuwiehyiuwahdjsahjfhajshfjwhuhejwhjehwjehjwhejwhejwhejwhejwhejhwjeh";
    String pat = "riuwheujwlhadfhsaljfhjahwjehjwhiuehyuwiehyiuwahdjsahjfhajshfjwhuhejwhjehwjehjwhejwhej";
    utils.println(bf(str, pat));
    utils.println(rk(str, pat));
    utils.println(kmp(str, pat));
    utils.println(bm(str, pat));
    utils.println(sunday(str, pat));
  }
}
