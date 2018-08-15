package LinkedList;

/**
 * 一些链表相关题
 */
public class LinkedListRelated {

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
     * 给出一个链表，每个节点包含一个额外增加的随机指针可以指向链表中的任何节点或空的节点。返回一个深拷贝的链表。
     *
     * 这是一道经典题，主要是random指向的节点要对应到新拷贝的节点上
     * 思路：首先依次拷贝整个链表并插入原来的链表中，如A->B->C->null =》 A->A->B->B->C->C->null （后一个是新拷贝出来的）
     *       然后将拷贝得到的A.random指向原来A.random.next,最后将链表拆开即可
     */
    public ListNode copyRandomList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        while (cur != null) { //拷贝并插入
            ListNode tmp = cur.next;
            cur.next = new ListNode(cur.val);
            cur.next.next = tmp;
            cur = tmp;
        }
        cur = head;
        while (cur != null) { //random指向
            ListNode tmp = cur.next;
            if (cur.random != null) {
                tmp.random = cur.random.next;
            }
            cur = tmp.next;
        }
        ListNode tmp = head.next;
        ListNode newHead = head.next;
        head.next = tmp.next;
        cur = head.next;
        while (cur != null) { //拆分链表
            tmp.next = cur.next;
            tmp = tmp.next;

            cur.next = tmp.next;
            cur = cur.next;
        }
        return newHead;
    }



}
