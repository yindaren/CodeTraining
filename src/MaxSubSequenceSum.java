/**
 * 数列子序列之和的最大值。如[1, -2, 2, 2, 3, -1]最大子序列为[2, 2, 3](7)
 */
public class MaxSubSequenceSum {
    /**
     * [0 - i]最大子序列（preMax）可能在[0 - i-1]中（不含最后的第i个元素），也可能包含最后的元素。记包含最后一个元素i的最大子序列为preBaseMax
     * 考虑[0 - i+1]，更新preBaseMax得到包含最后一个元素i+1的最大子序列，与preMax比较并更新preMax
     * 有点动态规划的意思（O（N）），暴力搜索（O（N^2）），
     * 也可以采取分治法（类似归并排序的思想，O（NlogN））：[ A[N/2] B[N/2] ], 最大子序列为A、B两部分最大序列值与横跨中心的最大序列值中的最大者。
     * 横跨中心的最大序列为A包含边界的最大序列与B包含边界的最大序列之和
     * @param A
     * @return
     */
    public static int maxSum(int[] A) {
        int len = A.length;
        int preMax = Integer.MIN_VALUE;
        int preBaseMax = Integer.MIN_VALUE;
        int next = 0;
        while (next < len) {//这里也可以记录数列序号，以便得到实际的子序列
            preBaseMax = Math.max(A[next], preBaseMax + A[next]);//更新preBaseMax
            preMax = Math.max(preBaseMax, preMax);//更新preMax
            next ++;
        }
        return preMax;
    }

    public static void main(String[] Args) {
        System.out.println(MaxSubSequenceSum.maxSum(new int[]{0}));
    }
}
