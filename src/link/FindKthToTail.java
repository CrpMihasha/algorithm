package link;

import newcode.ListNode;

public class FindKthToTail {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param pHead ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {
        if(pHead == null){
            return null;
        }
        // write code here
        ListNode postHead = reverse(pHead);
        ListNode copyPostHead = postHead;
        int count = 0;
        while(count <= k && postHead != null){
            count++;
            postHead = postHead.next;
        }
        if(count < k){
            return null;
        }
        postHead.next = null;
        return reverse(copyPostHead);

    }

    public ListNode reverse(ListNode head){
        ListNode pre = null, cur = head;
        while(cur != null){
            head = head.next;
            cur.next = pre;
            pre = cur;
            cur = head;
        }
        return pre;
    }
}
