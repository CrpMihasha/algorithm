package link;

import newcode.ListNode;

import java.util.*;

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 *   public ListNode(int val) {
 *     this.val = val;
 *   }
 * }
 */

public class ReverseBetween {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param head ListNode类
     * @param m int整型
     * @param n int整型
     * @return ListNode类
     */
    public ListNode reverseBetween (ListNode head, int m, int n) {
        // 需要记住的节点， 虚拟头节点，反转前一个节点，反转头节点，反转尾节点，反转尾+1节点
        ListNode dummy = new ListNode(-11111);
        dummy.next = head;
        ListNode dummyCopy = dummy, reversePre = null,reverseHead = null, reverseTail = null, reverseTailNext = null;
        for(int i = 0;i<=n;i++){
            if(i == m-1){
                reversePre = dummy;
            }
            if(i == m){
                reverseHead = dummy;
            }
            if(i == n){
                reverseTail = dummy;
                reverseTailNext = dummy.next;
                reverseTail.next = null;
                reverse(reverseHead);
            }
            dummy = dummy.next;
        }
        reversePre.next = reverseTail;
        reverseHead.next = reverseTailNext;
        return dummyCopy.next;

    }

    private ListNode reverse(ListNode node){
        ListNode pre = null, cur = node;
        while(cur != null){
            node = cur.next;
            cur.next = pre;
            pre = cur;
            cur = node;
        }
        return pre;
    }
}
