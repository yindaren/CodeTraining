package LinkedList;

public class ListNode {
    public static ListNode createList(int[] num) {
        ListNode result = new ListNode(0);
        ListNode tmp = result;
        for(int n: num) {
            tmp.next = new ListNode(n);
            tmp = tmp.next;
        }
        return result.next;
    }

    int val;
    ListNode next;
    ListNode random;
    ListNode(int val)
    {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
