package treeAndGraph;

import java.util.Stack;

/**
 *
 * 线段树是一棵二叉树，他的每个节点包含了两个额外的属性start和end用于表示该节点所代表的区间。start和end都是整数，并按照如下的方式赋值:
 * 根节点的 start 和 end 由 build 方法所给出。
 * 对于节点 A 的左儿子，有 start=A.left, end=(A.left + A.right) / 2。
 * 对于节点 A 的右儿子，有 start=(A.left + A.right) / 2 + 1, end=A.right。
 * 如果 start 等于 end, 那么该节点是叶子节点，不再有左右儿子。
 * 比如给定start=1, end=6，对应的线段树为：（每一个线段树节点还需维护区间内的最小值或者最大值或者其对应的下标或者计数等）
 *                 [1,  6] value=
 *               /        \
 *       [1,  3] value=      [4,  6] value=
 *       /     \           /     \
 *     [1, 2]  [3,3]     [4, 5]   [6,6]
 *     /    \           /     \
 *  [1,1]   [2,2]   [4,4]   [5,5]
 *
 *  线段树对连续的区间操作有用，如区间内的最小值或者最大值或者其对应的下标等。
 *
 *  线段树还可以直接用数组来实现，详见http://blog.csdn.net/metalseed/article/details/8039326
 *
 *  线段树的轻量级替代品：树状数组(主要是区间和)
 */
public class SegmentTree {

    /**
     * 线段树节点
     */
    public static class SegmentTreeNode {
        public int start, end, value;
        public SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end, int max) {
            this.start = start;
            this.end = end;
            this.value = max;
            this.left = this.right = null;
        }

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = this.right = null;
        }
    }

    /**
     *@param start, end: Denote an segment / interval
     *@return: The root of Segment Tree
     */
    public static SegmentTreeNode build(int[] A, int start, int end) {
        // write your code here
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new SegmentTreeNode(start, end, A[start]);
        }
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        int mid = (start + end) / 2;
        root.left = build(A, start, mid);
        root.right = build(A, mid + 1, end);
        root.value = root.left.value > root.right.value ? root.left.value : root.right.value;
        return root;
    }

    /**
     * 该方法将 root 为跟的线段树中 [start, end] = [index, index] 的节点修改为了新的 value ，
     * 并确保在修改后，线段树的每个节点的 value 属性仍然具有正确的值(这里实现的value代表的是最大值)。
     */
    public void modify(SegmentTreeNode root, int index, int value) {
        SegmentTreeNode node = root;
        if (node == null || node.start > index || node.end < index) {
            return;
        }
        Stack<SegmentTreeNode> s = new Stack<>();//用栈代替递归
        SegmentTreeNode tmp = null;
        int start = node.start;
        int end = node.end;
        while (start != end) {
            s.push(node);
            int mid = (node.start + node.end) / 2;
            if (mid >= index) {
                node = node.left;
            } else {
                node = node.right;
            }
            start = node.start;
            end = node.end;
        }

        node.value = value;
        while(!s.empty()) {
            tmp = s.pop();
            int pre = tmp.value;
            int left = tmp.left.value;
            int right = tmp.right.value;
            tmp.value = left > right ? left : right;
            if (tmp.value == pre) {
                //s.clear();
                break;
            }
        }
    }

    /**
     * 查找给定区间内的最大值
     */
    public static int query(SegmentTreeNode root, int start, int end) {
        if (start > end || start > root.end || end < root.start) {
            return Integer.MIN_VALUE;
        }
        if (root.start >= start && root.end <= end) {
            return root.value;
        }
        int mid = (root.start + root.end) / 2;
        if (mid >= end) {
            return query(root.left, start, end);
        } else if (mid < start) {
            return query(root.right, start, end);
        } else {
            int left = query(root.left, start, mid);
            int right = query(root.right, mid + 1, end);
            return left > right ? left : right;
        }
    }

}
