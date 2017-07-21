/**
 * 一些链表相关题
 */
public class LinkedListRelated {

    public static class ListNode {
        int val;ListNode next;
        ListNode(int val)
        {
            this.val = val;
            this.next = null;
        }
    }

    /**
     * 判断链表中是否有环
     * 思路：一个节点走两格，一个走一格，若有环将会相遇
     * 注意，该方法还可用于找链表的中间节点
     */
    public boolean hasCycle(ListNode head) {
        ListNode n1 = head;
        if (n1 == null) {
            return false;
        }
        ListNode n2 = head;
        boolean flag = false;
        while (n2.next != null) {
            n2 = n2.next;
            if (n2.next != null) {
                n2 = n2.next;
            } else {
                break;
            }
            n1 = n1.next;
            if (n1 == n2) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 给定一个链表，旋转链表，使得每个节点向右移动k个位置，其中k是一个非负数.
     * 给出链表1->2->3->4->5->null和k=2,返回4->5->1->2->3->null
     *
     * 思路：k = k % len，找到最后的k个元素，直接接到最前面即可。如上例中将最后两个4->5接到最前面。
     * 类比：如果是数组，可以先将后k个元素逆转，再将前面len-k个元素逆转，最后整体逆转。
     */
    public ListNode rotateRight(ListNode head, int k) {
        // write your code here
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        ListNode tmp = head;
        int len = 1;
        while (tmp.next != null) {
            tmp = tmp.next;
            len ++;
        }
        k %= len;
        if (k == 0) {
            return head;
        } else {
            tmp.next = head;
            tmp = head;
            for (int i = 0; i < len - k - 1; i ++) {
                tmp = tmp.next;
            }
            ListNode newHead = tmp.next;
            tmp.next = null;
            return newHead;
        }
    }

    /**
     * 在 O(n log n) 时间复杂度和常数级的空间复杂度下给链表排序。
     *
     * 给出 1->3->2->null，给它排序变成 1->2->3->null.
     */
    public static class sort {

        /**
         * 快速排序
         */
        public ListNode quickSort(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            int val = head.val;
            ListNode tmp = head.next;
            ListNode small = new ListNode(0);
            ListNode big = new ListNode(0);
            ListNode tmp1 = small;
            ListNode tmp2 = big;
            while (tmp != null) {
                if (tmp.val < val) {
                    tmp1.next = tmp;
                    tmp1 = tmp;
                } else {
                    tmp2.next = tmp;
                    tmp2 = tmp;
                }
                tmp = tmp.next;
            }
            tmp1.next = null;
            tmp2.next = null;
            if (small.next == null) {
                head.next = quickSort(big.next);
                return head;
            } else if (big.next == null) {
                ListNode node = quickSort(small.next);
                getTail(node).next = head;
                head.next = null;
                return node;
            } else {
                ListNode node = quickSort(small.next);
                getTail(node).next = head;
                head.next = quickSort(big.next);
                return node;
            }
        }

        public ListNode getTail(ListNode head) {
            ListNode tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            return tmp;
        }


        /**
         * 归并排序
         */
        public ListNode mergeSort(ListNode head) {
            if(head == null || head.next == null) return head;
            ListNode mid = findMid(head);

            ListNode tmp = mid.next;
            mid.next = null;
            ListNode headleft = mergeSort(head);
            ListNode headright = mergeSort(tmp);
            return merge(headleft, headright);
        }

        /**
         * 找到归并排序的中间节点
         */
        public static ListNode findMid(ListNode head){
            if(head == null || head.next == null) return head;
            ListNode slow = head;
            ListNode fast = head;
            while(fast.next != null && fast.next.next != null){
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

        /**
         * 归并两个子链表
         */
        public static ListNode merge(ListNode left, ListNode right){
            if(left == null || right == null) return null;
            ListNode node = new ListNode(0);
            ListNode dummy = node;
            while(left != null && right != null){
                if(left.val < right.val){
                    ListNode tmp = left;
                    left = left.next;
                    dummy.next = tmp;
                    dummy = dummy.next;
                }else{
                    ListNode tmp = right;
                    right = right.next;
                    dummy.next = tmp;
                    dummy = dummy.next;
                }
            }
            if(left != null) dummy.next = left;
            else dummy.next = right;
            return node.next;
        }
    }
}
