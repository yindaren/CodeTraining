package string;

/**
 * 给定一个字符串，找到最多有k个不同字符的最长子字符串。
 * 例如，给定 s = "eceba" , k = 3, T 是 "eceb"，长度为 4.
 *
 * 思路：双指针，一个指针朝前走并记录不同字符数num及其对应数量（flag数组中），直到不能再加入字符（num = k 后又出现新的字符），
 * 比较此时字符长度。此时前一个指针往前走，并依次减去路过的字符的数量，直到某一个字符减为0， 此时右边的指针又可以继续往前走。
 * 循环进行，直到遍历完。
 *
 * 注意：找到的子字符串中含有的不同字符可能小于k，比如整个字符串含有的字符数就少于k
 */
public class LongestSubstringKDistinct {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        if(s==null || s.length()==0 || k ==0) return 0;
        int n = s.length();
        if(k>=n) return n;
        int i=0,j=0;
        int[] flag = new int[128];
        int num = 0;
        int max = Integer.MIN_VALUE;
        while(j<n) {
            char c = s.charAt(j);
            if(flag[c]!=0) {
                flag[c] += 1;
                j++;
            }
            else {
                if(num == k) {
                    max = Math.max(max, j-i);
                    while(i<j) {
                        flag[s.charAt(i)] -= 1;
                        if(flag[s.charAt(i++)] == 0) {
                            num--;
                            break;
                        }
                    }
                }
                else {
                    flag[c] += 1; //注意这里也要+1，不要漏掉
                    num ++;
                    j++;
                }
            }
        }
        max = Math.max(j-i, max); // 一定不能漏掉这里，整个字符串或者最后一次循环中含有的字符数可能少于k
        return max;
    }
}
