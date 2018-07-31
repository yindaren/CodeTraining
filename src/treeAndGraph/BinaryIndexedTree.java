package treeAndGraph;

import java.util.Arrays;

/**
 * 树状数组(Binary Indexed Tree(B.I.T), Fenwick Tree)是一个查询和修改复杂度都为log(n)的数据结构，
 * 主要用于查询任意两位之间的所有元素之和。
 *
 * 原理：
 * 根据二进制的组成，可以得出，任何一个数i都可以表示成2的幂次方的和的一个组成形式，而树状数组C[i]
 * 表示的是2^k=lowbit(i)个元素相加的和，这些元素是从第i个元素往下数i一直递减而得到的。
 *
 * 参考：
 * http://blog.csdn.net/ljd4305/article/details/10101535
 *
 * 题目：
 * 给定一个整数数组（下标由 0 到 n-1， n 表示数组的规模，取值范围由 0 到10000）。对于数组中的每个 ai 元素，
 * 请计算 ai 前的数中比它小的元素的数量。例如，对于数组[1,2,7,8,5] ，返回 [0,1,2,3,2]。实现见main函数
 *
 * 基本思路类似基数排序，统计0-10000出现的次数（数组）。首先初始化一个C[10001]的树状数组（初始A全为0，C也全为0），
 * 每读一个数（假设为a）则更新数组，并统计小于a的数的个数，即计算A[1]+A[2]+A[2]+...+A[a]（把index 0 看成 1），
 * 若a=(110101000) = 2^8+2^7+2^5+2^3，则这个和为C[100000000] + c[11000000] + + C[110100000] + c[110101000],
 * 其中，C[100000000]为A[100000000]往前数的2^8个数，C[11000000]为A[11000000]往前数的2^7个数，依次类推。
 *
 */
public class BinaryIndexedTree {
    /**树状数组，c[k]表示从a[k]开始往左连续求lowbit(k)个数的和，看成是从1开始记录索引
     * 貌似还是直接不用第零位比较方便，懒得改了
     */
    private final int[] C;
    private final int[] A;//灵活选择要不要
    private final int len;

    /**
     * @param A 原数组
     */
    public BinaryIndexedTree(int[] A) {//可以考虑把A也作为一个成员变量
        this.A = A;
        len = A.length;
        C = new int[len];
        built();
    }

    public BinaryIndexedTree(int length) {
        len = length;
        A = new int[length];
        C = new int[length];
    }

    /**
     * lowbit(k)就是把k的二进制的高位1全部清空，只留下最低位的1,
     * 比如10的二进制是1010,则lowbit(k)=lowbit(1010)=0010(2进制)
     * 另一种理解是若k的二进制末尾有m个零，则lowerBit(k) = 2^m.
     */
    private int lowerBit(int k) {
        return k & -k;//源码与补码
    }

    private void built() {
        for (int i = 1; i <= len; i ++) {
            int m = lowerBit(i);
            int base = i - m; //10100 - 00100 = 10000
            int tmp = A[i - 1]; //i = 1对应index 0
            while ((m = m >>> 1) != 0) {
                base += m;
                tmp += C[base - 1];//base + m +...... 依次为10010，10011
            }
            C[i-1] = tmp;
        }
    }

    /**
     * 修改A中的一个数,以增量的形式
     */
    public BinaryIndexedTree update(int index, int increment) {
        A[index] += increment;
        index++;//下标看成从1开始
        int parent = index;
        while(parent <= len) {
            C[parent -1] += increment;
            parent += lowerBit(parent);//index + lowerBit(index)可得到子节点index的父节点，修改值只影响父节点
        }
        return this;
    }

    /**
     * 查询某一区间内的累加值, 如A[2] - A[5]
     */
    public int query(int start, int end) {
        end = end >= len ? len : end + 1;

        int s, e;
        s = e = 0;
        int lowerBit = 0;
        while ((start -= lowerBit) > 0) {
            lowerBit = start & - start;
            s += C[start - 1];
        }

        do {
            lowerBit = end & - end;
            e += C[end - 1];
        } while ((end -= lowerBit) != 0);
        return e - s;
    }

    /**
     * 得到A[0] +　A[1] + ....+ A[num]的值,看成是1到num
     * @param num 求和的数量
     */
    public int getSum(int num) {
        return query(0, num);
    }

    @Override
    public String toString() {
        return "treeAndGraph.BinaryIndexedTree{" +
                "C=" + Arrays.toString(C) +
                '}';
    }

    public static void main(String[] args) {
        int[] A = new int[]{1,2,7,8,5};
        BinaryIndexedTree tree = new BinaryIndexedTree(10001);
        //System.out.println(tree.getSum(0));A
        //System.out.println(tree.update(2,7, A[2]));
        for (int i = 0; i < A.length; i++) {
            tree.update(A[i], 1);
            System.out.println(tree.getSum(A[i] - 1));
        }
    }
}
