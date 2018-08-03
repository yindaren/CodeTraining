package string;

/**
 * 与2不同：字母在目标字符串中不需要具有相同的顺序(从这一步可知：只需关注出现的数量)
 */
public class StringMatch3 {
    /**
     * 给定一个字符串source和一个目标字符串target，在字符串source中找到包括所有目标字符串字母的子串(最短的那一个)。
     * 如果在source中没有这样的子串，返回""，如果有多个这样的子串，返回起始位置最小的子串，
     * 在答案的子串中的字母在目标字符串中不需要具有相同的顺序
     * 给出source = "ADOBECODEBANC"，target = "ABC" 满足要求的解  "BANC"
     *
     * 思路：
     * 首先预处理T，用一个128长 (示例代码用了256) 的整数数组srcHash存储里面每个目标字符出现的个数(大小写字母的ASCII码不大于128)
     * 然后处理原串S，也用一个128长的整数数组destHash记录原串字符出现的个数。给定两个指针start和end，作为结果的左右窗口。
     *
     * 关键即在于不需要保持顺序，那么只要维持数量即可。
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

}
