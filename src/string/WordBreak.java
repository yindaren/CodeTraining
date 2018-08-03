package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * word break 问题汇总
 */
public class WordBreak {
  /**
   * 给出一个字符串s和一个词典，判断字符串s是否可以被空格切分成一个或多个出现在字典中的单词。
   * 如：s = "lintcode"， dict = ["lint","code"]，返回true
   * 思路：使用动态规划，注意不要在循环中使用String +（单个string+效率并不低，但在循环中需要每次申请堆上空间，因为string是final的），用StringBuffer代替
   *
   * @param s the target string
   * @param dict the dictionary
   */
  public boolean wordBreak(String s, Set<String> dict) {
    int len = s.length();
    if (dict.contains(s) || len == 0) {
      return true;
    } else if (len < 2) {
      return false;
    }
    int maxLen = 0;
    for (String tmp : dict) {
      maxLen = maxLen > tmp.length() ? maxLen : tmp.length();
    }
    boolean[] flag = new boolean[len];//记录子问题（0 - len 的子串）是否可切
    StringBuffer buf = new StringBuffer();
    //String str = "";
    for (int i = 0; i < len; i++) {
      buf.append(s.charAt(i));
      String str =  buf.toString();//或者用substring，但不要用str += s.charAt(i)，导致超过运行时间限制
      int lower = i - maxLen > 0 ? i - maxLen : 0;
      for (int j = i - 1; j >= lower; j--) {//超过字典中最大词的长度就不必继续计算了
        if (!flag[j]) continue;
          // 这里不用考虑j+1 到i+1之间是否能分成多个断，若能则之前的循环早就出结果了
        else if (dict.contains(s.substring(j + 1, i + 1))) {
          flag[i] = true;
          break;
        }

      }
      if (!flag[i] && i <= (maxLen - 1) && dict.contains(str)) {
        flag[i] = true;
      }
    }
    return flag[len - 1];
  }

  /**
   * 类似上一题， 但要将所有的可能组合返回。例如： dict = ["de", "ding", "co", "code", "lint"]，返回["lint code", "lint co de"]
   * 动态规划解法，无法通过时空限制（可考虑先跑一下上一个wordBreak判断是否可切，这样能通过，但太傻了，主要是其中一个不可切的case太费时）
   *
   */
  public List<String> wordBreak_2_1(String s, Set<String> wordDict) {
    int len = s.length();
    Map<Integer, List<String>> map = new HashMap<>();//记录每一个可切分子串的所有可能切法
    int maxLen = 0;
    for (String tmp : wordDict) {
      maxLen = Math.max(tmp.length(), maxLen);
    }
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < len; i++) {
      buf.append(s.charAt(i));
      String str = buf.toString();
      List<String> tmp = new ArrayList<>();
      if (i < maxLen && wordDict.contains(str)) {
        tmp.add(str);
      }
      if (i > maxLen && map.containsKey(i - maxLen - 1)) {//之前的map用不上了
        map.remove(i - maxLen - 1);
      }
      int lower = Math.max(0, i - maxLen);
      for (int j = i - 1; j >= lower; j--) {
        String tmpStr1 = s.substring(j + 1, i + 1);
        if (map.containsKey(j) && wordDict.contains(tmpStr1)) {
          for (String tmpStr2 : map.get(j)) {
            // StringBuffer tmpBuf = new StringBuffer(tmpStr2);
            // tmpBuf.append(" ");
            // tmpBuf.append(tmpStr1);
            // tmp.add(tmpBuf.toString());
            tmp.add(tmpStr2 + " " + tmpStr1);
          }
        }
      }
      if (tmp.size() > 0 || i == len - 1) {
        map.put(i, tmp);
      }
    }
    return map.get(len - 1);
  }

  /**
   * 递归辅助方法
   */
  public void help(String s, Set<String> wordDict,String res, int maxLen, List<String> list) {
    int len = s.length();
    if (len == 0) {
      list.add(res);
      return;
    }
    for (int i = 0; i < len && i < maxLen; i ++) {
      String tmp = s.substring(0, i + 1);
      if (wordDict.contains(tmp)) {
        help(s.substring(i + 1, len), wordDict, res + " " + tmp, maxLen, list);
      }
    }
  }

  /**
   * 接上一题，brute force解法，同DP方法，需要先用wordBreak判断是否可切才能通过测试
   */
  public List<String> wordBreak_2_2(String s, Set<String> wordDict) {
    int len = s.length();
    List<String> list = new ArrayList<>();
    //int[] flag = new int[len];
    int maxLen = 0;
    for (String tmp : wordDict) {
      maxLen = Math.max(tmp.length(), maxLen);
    }
    for (int i = 0; i < len && i < maxLen; i ++) {
      String tmp = s.substring(0, i + 1);
      if (wordDict.contains(tmp)) {
        help(s.substring(i + 1, len), wordDict, tmp, maxLen, list);
      }
    }
    return list;
  }
}
