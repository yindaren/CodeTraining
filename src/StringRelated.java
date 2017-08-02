import java.util.*;

/**
 * 字符串相关
 */
public class StringRelated {
    /**
     * 给定一个字符串source和一个目标字符串target，在字符串source中找到包括所有目标字符串字母的子串(最短的那一个)。
     * 如果在source中没有这样的子串，返回""，如果有多个这样的子串，返回起始位置最小的子串，在答案的子串中的字母在目标字符串中不需要具有相同的顺序
     * 给出source = "ADOBECODEBANC"，target = "ABC" 满足要求的解  "BANC"
     *
     * 思路：
     * 首先预处理T，用一个128长 (示例代码用了256) 的整数数组srcHash存储里面每个目标字符出现的个数(大小写字母的ASCII码不大于128)
     * 然后处理原串S，也用一个128长的整数数组destHash记录原串字符出现的个数。给定两个指针start和end，作为最小窗口的左右边界。
     *
     * 要求复杂度为O（n），该算法最多为2n，满足要求
     *
     */
    public String minWindow(String S, String T) {
        int[] srcHash = new int[255];
        // 记录目标字符串每个字母出现次数
        for(int i = 0; i < T.length(); i++){
            srcHash[T.charAt(i)]++;
        }
        // 用于记录窗口内每个字母出现次数
        int[] destHash = new int[255];
        int found = 0;
        int begin = -1, end = S.length(), minLength = S.length();//注意初始化技巧
        for(int start = 0,i= 0; i < S.length(); i++){
            // 每来一个字符给它的出现次数加1
            destHash[S.charAt(i)]++;
            // 如果加1后这个字符的数量不超过目标串中该字符的数量，则找到了一个匹配字符
            if(destHash[S.charAt(i)] <= srcHash[S.charAt(i)]) found++;
            // 如果找到的匹配字符数等于目标串长度，说明找到了一个符合要求的子串
            if(found == T.length()){
                // 将开头没用的都跳过，没用是指该字符出现次数超过了目标串中出现的次数，并把它们出现次数都减1
                while(start < i && destHash[S.charAt(start)] > srcHash[S.charAt(start)]){ //算上while顶多遍历两遍数组，总复杂度不超过O（2n）
                    destHash[S.charAt(start)]--;
                    start++;
                }
                // 这时候start指向该子串开头的字母，判断该子串长度
                if(i - start < minLength){
                    minLength = i - start;
                    begin = start;
                    end = i;
                }
                // 把开头的这个匹配字符跳过，并将匹配字符数减1
                destHash[S.charAt(start)]--;
                found--;
                // 子串起始位置加1，我们开始看下一个子串了
                start++;
            }
        }
        // 如果begin没有修改过，返回空
        return begin == -1 ? "" : S.substring(begin,end + 1);
    }

    /**
     * word break 问题汇总
     */
    public static class WordBreak {
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

}
