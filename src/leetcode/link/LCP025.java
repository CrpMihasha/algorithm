package leetcode.link;

import java.util.List;
import java.util.Objects;

public class LCP025 {
    public static void main(String[] args) {
        LCP025 lcp025 = new LCP025();
        ListNode listNode = lcp025.addTwoNumbers(lcp025.l1(), lcp025.l2());
        lcp025.printListNode(listNode);
    }

    void printListNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val);
            System.out.print(" ");
            node = node.next;
        }
    }

    ListNode l1(){
        ListNode one = new ListNode(1);
        return one;
    }
    ListNode l2(){
        ListNode one = new ListNode(9);
        ListNode two = new ListNode(9);
        one.next = two;
        return one;
    }

    /**
     * 给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
     *
     * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
     *
     * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
     * 输出：[7,8,0,7]
     * 示例2：
     *
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[8,0,7]
     * 示例3：
     *
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 反转2条链表
        ListNode reverseL1 = reverseListNode(l1);
        ListNode reverseL2 = reverseListNode(l2);

        ListNode reverseL1Pre = reverseL1;
        ListNode ans = reverseL1;
        int carry = 0;
        // 从头开始相加到reverseL1上
        while (reverseL1 != null || reverseL2 != null) {
            if (reverseL1 != null) {
                int val = reverseL1.val + (reverseL2 == null ? 0 : reverseL2.val) + carry;
                if (val >= 10) {
                    carry = 1;
                    val = val - 10;
                }
                reverseL1.val = val;
                reverseL1Pre = reverseL1;
            } else {
                ListNode newNode = new ListNode(0);
                newNode.val = reverseL2.val + carry;
                reverseL1Pre.next = newNode;
                reverseL1Pre = newNode;
            }
            carry = 0;
            reverseL1 = reverseL1 == null ? null : reverseL1.next;
            reverseL2 = reverseL2 == null ? null : reverseL2.next;
        }
        if (carry == 1) {
            ListNode tail = new ListNode(1);
            reverseL1Pre.next = tail;
        }
        // 再次反转
        return reverseListNode(ans);
    }

    public ListNode reverseListNode(ListNode node) {
        if (Objects.isNull(node)) {
            return node;
        }
        ListNode pre = null;
        ListNode next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
