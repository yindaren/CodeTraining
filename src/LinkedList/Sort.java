package LinkedList;

public class Sort {
    /**
     * 用插入排序对链表排序
     * 数组可以从后往前或者从前往后，但链表只能从前往后。思路简单，实现的时候需小心
     */
    public ListNode insertionSortList(ListNode head) {
        // write your code here
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head.next;
        //ListNode pre = head;
        while (cur != null) {
            ListNode curTmp = dummy.next;
            ListNode preTmp = dummy;
            while (curTmp.val < cur.val) {
                preTmp = preTmp.next;
                curTmp = curTmp.next;
            }
            if (curTmp == cur) {
                cur = cur.next;
            } else {
                preTmp.next = cur;
                ListNode tmp = cur.next;
                cur.next = curTmp;
                while (curTmp.next != cur) {
                    curTmp = curTmp.next;
                }
                curTmp.next = tmp;
                cur = tmp;
            }
        }
        return dummy.next;
    }



    /**
     * 在 O(n log n) 时间复杂度和常数级的空间复杂度下给链表排序。
     *
     * 给出 1->3->2->null，给它排序变成 1->2->3->null.
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
