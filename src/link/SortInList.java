package link;

import newcode.ListNode;

public class SortInList {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param head ListNode类 the head node
     * @return ListNode类
     */
    public ListNode sortInList (ListNode head) {
        if(head.next == null){
            return head;
        }
        // write code here
        ListNode slow = head, fast = head.next;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tail = slow.next;
        slow.next = null;
        ListNode sort1 = sortInList(head);
        ListNode sort2 = sortInList(tail);
        return merge(sort1,sort2);
    }

    public ListNode merge(ListNode head1, ListNode head2){
        ListNode ans = null, cur = null;
        while(head1 != null && head2 != null){
            ListNode next = null;
            if(head1.val < head2.val){
                next = head1;
                head1 = head1.next;
            }else {
                next = head2;
                head2 = head2.next;
            }
            if(ans == null){
                ans = next;
            }
            if(cur == null){
                cur = next;
            }else{
                cur.next = next;
                cur = cur.next;
            }
        }

        if(head1 != null){
            cur.next = head1;
        }
        if(head2 != null){
            cur.next = head2;
        }
        return ans;
    }
}
