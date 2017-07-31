import java.util.Set;

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
        boolean[] flag = new boolean[len];
        StringBuffer buf = new StringBuffer();
        //String str = "";
        for (int i = 0; i < len; i++) {
            buf.append(s.charAt(i));
            String str =  buf.toString();//或者用substring，但不要用str += s.charAt(i)，导致超过运行时间限制
            int lower = i - maxLen > 0 ? i - maxLen : 0;
            for (int j = i - 1; j >= lower; j--) {
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
}
