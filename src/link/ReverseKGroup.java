package link;

import newcode.ListNode;

public class ReverseKGroup {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param head ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup (ListNode head, int k) {
        // write code here
        if(head == null || k == 1){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        int count = 0;
        // 4个： pre, reverseHead, reverseTail, reverseTailNext;
        ListNode pre = null, reverseHead = null, reverseTail = null, reverseTailNext = null;
        while(cur != null){
            if(count == 0){
                pre = cur;
            }
            if(count == 1){
                reverseHead = cur;
            }
            if(count == k){
                reverseTail = cur;
                reverseTailNext = cur.next;
                // break
                reverseTail.next = null;
                reverse(reverseHead);
                // link
                pre.next = reverseTail;
                reverseHead.next = reverseTailNext;
                cur = reverseHead;
                reverseTail = null;
                reverseHead = null;
                reverseTailNext = null;
                count = 0;
            }else{
                count++;
                cur = cur.next;
            }
        }
        pre.next = reverseHead;



        return dummy.next;
    }

    public ListNode reverse(ListNode head){
        ListNode pre = null, cur = head;
        while(cur != null){
            head = cur.next;
            cur.next = pre;
            pre = cur;
            cur = head;
        }
        return pre;
    }
}
