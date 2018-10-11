package permutation;

public class NextAndPreviousPermutation {
  /**
   * 给定一个整数数组来表示排列，找出其之后的一个排列。
   *
   * 给出排列[1,3,2,3]，其下一个排列是[1,3,3,2]
   * 给出排列[4,3,2,1]，其下一个排列是[1,2,3,4]
   *
   * 思路：在纸上画一画容易理解这个过程。对于[a,b,c,d,e],从尾到头依次与后一位比较，若d<e,则直接交换d,e;
   * 否则继续比较,假设有c>d>e且b<c, 那么在c，d，e中找到大于b且与b最接近的数（假定为d），并交换位置，并将c，b, e是递减的，需要将其顺序反过来
   * 特殊情况：最后的排列如3，2，1结果为1，2，3
   */
  public int[] nextPermutation(int[] nums) {
    int length = nums.length;
    if(length <= 1) return nums;
    int i = length-2;
    for(; i>=0;i--) {
      if(nums[i]<nums[i+1]) { // 找到交换点，只用小于可以跳过重复数字
        int k = length-1;
        for(;k>=i+1;k--) { //找到第一个大于num[i]的数
          if(nums[k] > nums[i]) break;
        }
        int tmp = nums[i];
        nums[i] = nums[k];
        nums[k] = tmp;
        break;
      }
    }

    i++;// 此处i不可能为0，并且自然的处理了特殊情况
    for(int m=i, n=length-1;m<n;m++, n--) {
      int tmp = nums[m];
      nums[m] = nums[n];
      nums[n] = tmp;
    }
    return nums;
  }

  /**
   * 给定一个整数数组来表示排列，找出其上一个排列
   *
   * 类似上一题相反的过程：对于[a,b,c,d,e],从尾到头依次与后一位比较，若d>e,则直接交换d,e;
   * 否则继续比较,假设有c<d<e且b>c, 那么在c，d，e中找到小于b且与b最接近的数（假定为d），并交换位置，并将c，b, e是递增的，需要将其顺序反过来
   * 特殊情况：最后的排列如3，2，1结果为1，2，3
   */
  public int[] previousPermuation(int[] nums) {
    // write your code here
    int length = nums.length;
    if(length <= 1) return nums;
    int i = length-2;
    for(; i>=0;i--) {
      if(nums[i]>nums[i+1]) { // 找到交换点
        int k = length-1;
        for(;k>=i+1;k--) { //找到第一个小于num[i]的数
          if(nums[k] < nums[i]) break;
        }
        int tmp = nums[i];
        nums[i] = nums[k];
        nums[k] = tmp;
        break;
      }
    }

    i++;// 此处i不可能为0，并且自然的处理了特殊情况
    for(int m=i, n=length-1;m<n;m++, n--) {
      int tmp = nums[m];
      nums[m] = nums[n];
      nums[n] = tmp;
    }
    return nums;
  }
}
