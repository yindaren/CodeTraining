package bit;

import java.util.ArrayList;
import java.util.List;

public class SingleNumber {
    /**
     * 题一：给出2*n + 1 个的数字，除其中一个数字之外其他每个数字均出现两次，找到这个数字。
     *
     * 1 遍历，排序，set，map等方法
     * 2 异或运算。异或运算具有结合律与交换律，且0^a=a, a^a=0 对于实例{2,1,4,5,2,4,1}就会有这样的结果：
     *             (2^1^4^5^2^4^1) => ((2^2)^(1^1)^(4^4)^(5)) => (0^0^0^5) => 5, 就能找出其中唯一不同的数了
     *
     * 注意: 只要其他数出现偶数次，要找的数出现奇数次，都可以用这个方法
     */
    public static int singleNumber(int[] A) {
        if(A == null || A.length == 0) {
            return -1;
        }
        int rst = 0;
        for (int i = 0; i < A.length; i++) {
            rst ^= A[i];
        }
        return rst;
    }

    /**
     * 题二：给出3*n + 1 个的数字，除其中一个数字之外其他每个数字均出现三次，找到这个数字。
     * 注意：这里要找的数字只出现一次
     *
     * 解法一： int 数据共有32位，可以用32变量存储 这 N 个元素中各个二进制位上  1  出现的次数，
     * 最后 在进行 模三 操作，如果为1，那说明这一位是要找元素二进制表示中为 1 的那一位。
     */
    public static int singleNumberII(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int result=0;
        int[] bits=new int[32];
        for (int i = 0; i < 32; i++) {
            for(int j = 0; j < A.length; j++) {
                bits[i] += A[j] >> i & 1;
                bits[i] %= 3; // 出现三次归零，最后剩下的只会出现一次的那个数在相应位上的bit值
            }

            result |= (bits[i] << i);
        }
        return result;
    }

    /**
     * 题二解法二：利用三个变量分别保存各个二进制位上 1 出现一次、两次、三次的分布情况，这里的三个数与方法一中bit数组含义类似
     * 且时间复杂度更好，只需要遍历一次，暂时未理解
     */
    public static int singleNumberII2(int[] A) {
        // todo: 未理解
        int one=0, two=0, three=0;
        for(int i=0; i<A.length; i++){
            two |= one&A[i];
            one^=A[i]; //A.length
            one&= ~three;
            two&= ~three;
        }
        return one;
    }

    /**
     * 题三：给出2*n + 2个的数字，除其中两个数字之外其他每个数字均出现两次，找到这两个数字。
     *
     * 假设只出现一次的两个数为b, c，异或A中所有的数得到了值m, 则 m = b^c，m中为1的位k表示 b， c在k位 不相同,
     * 根据第k位为1 或 0把A中的数分为两组，b, c出现在两个不同的组A1, A2中, 利用single Number 中的方法，
     * 对A1, A2中的数分别全部异或
     */
    public List<Integer> singleNumberIII(int[] A) {
        // write your code here
        int xor = 0;
        for (int i = 0; i < A.length; i++) {
            xor ^= A[i];
        }

        int lastBit = xor - (xor & (xor - 1)); // A&(A-1)会把最末尾的1变为0
        int group0 = 0, group1 = 0; //代表两个结果
        for (int i = 0; i < A.length; i++) {
            if ((lastBit & A[i]) == 0) {
                group0 ^= A[i];
            } else {
                group1 ^= A[i];
            }
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(group0);
        result.add(group1);
        return result;
    }

    /**
     * 题四：给定数组，除了一个数出现一次外，所有数都出现两次，并且所有出现两次的数都挨着。请找出找出那个出现一次的数。
     *
     * 二分长度，通过奇偶性找出落单的数
     */
    public static int getSingleNumber(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == nums[mid - 1]) {
                if (((mid - left + 1) & 1) == 1) {
                    right = mid - 2;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] == nums[mid + 1]) {
                if (((right - mid + 1) & 1) == 1) {
                    left = mid + 2;
                } else {
                    right = mid - 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[left];
    }
}
