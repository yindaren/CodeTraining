import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 中位数相关题 1 一个未排序数组 2 两个已排序数组 3 一个不断输入的数字流
 * 思路：均转化为找第k个数的问题，采用分治法
 *
 */

public class Median {

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 快排一部分，以nums[base]为基准，将数组分成两部分
     *
     * @param base 基准值的下标
     * @return 最终基准数据在数组中的位置下标
     */
    private static int quickSortPartition(int[] nums, int base, int start, int end) {
        if(nums == null || start < 0 || base < 0 || end >= nums.length || base >= nums.length || start > end) return -1;
        int tmp = nums[base];
        swap(nums, base, end);
        int p1= start-1; // p1指向【小于】-【大于】之间的分界线
        for(int i= start;i<end;i++) {
            if(nums[i]<tmp) {
                p1++;
                if(p1!=i) {//p1=i说明之前所有的都是小于tmp的，不需要移动，只有中间出现大于的数后，才需要移动
                    swap(nums, i, p1);
                }
            }
        }
        p1++;//返回选中节点最终的位置（在数组中的下标）
        swap(nums, p1, end);
        return p1;
    }

    /**
     * 题一：未排序数组找中位数
     * 1 排序后输出
     * 2 类似快排，分成两部分，分治解决(以下实现，前提是允许改变原数组，平均复杂度O（n），注意理解while条件)
     * 3 建堆实现（单个堆）
     * 4 类似
     *
     *
     *
     * 注：此题偶数也输出第n/2个数，不求均值
     * @param nums 未排序数组
     */
    static int median(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int target = (n-1)/2;
        int start = 0, end = n-1;
        int index = quickSortPartition(nums, start, start, end);
        while(index != target) {
            if(index > target) {
                end = index - 1;
            }
            else {
                start = index + 1;
            }
            index = quickSortPartition(nums, start, start, end);
        }
        return nums[index];
    }

    /**
     * 题二：两个排序数组的中位数
     * 1 归并排序
     * 2 分治法，找第k个数（以下实现）
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // todo(yll): 实现
        int m = A.length, n= B.length;
        int target = (m+n)/2;// 目标是找到第target个数
        int la=0, ra=0, lb=0, rb=0;
        while(true) {
            int tmpM = (ra - la + rb - lb + 2)/2;
            int ma = (la + ra)/2;
            int mb = (lb + rb)/2;
            //if(target > )
            break;
        }
        return -1;
    }

    /**
     * 题三：
     * 数字是不断进入数组的，在每次添加一个新的数进入数组的同时返回当前新数组的中位数。
     *
     * 注：此题偶数也输出第n/2个数，不求均值
     *
     * 思路1 ：按照题意中位数为A[(n-1)/2]，即如果数组长度为奇数，中位数是最中间的那个，如果长度为偶数是中间偏左的那个元素，
     * 我们假设以中位数作为数组的分割点，且将中位数始终划分到左半部分，那么数组的左半部分也可以看成一个最大堆，只需poll一次就
     * 可弹出当前的中位数，而右半部分就是最小堆，因为将中位数划分在左半部分，那么左半部分的长度永远等于或者比右半部分大1
     * (可以从数学归纳法去理解)
     *
     */
    public int[] medianFromDataStream(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // max heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // min heap

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            addNums(maxHeap, minHeap, nums[i]);
            result[i] = maxHeap.peek();
        }

        return result;
    }

    private void addNums(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, int num) {
        maxHeap.offer(num); //进行中间值的更新，从数学归纳法角度比较容易理解这里的逻辑
        minHeap.offer(maxHeap.poll());
        if (minHeap.size() - maxHeap.size() > 0) { // 左半部分的长度永远等于或者比右半部分大1
            maxHeap.offer(minHeap.poll());
        }
    }

    public static void main(String[] args) {

    }
}
