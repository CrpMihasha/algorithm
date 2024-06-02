package newcode;

import java.util.*;

public class ListNode {
    public int val;
    public ListNode next;

    public static void main(String[] args) {
        System.out.println(1000000007%1);
    }
    public static int search (int[] nums, int target) {
        if(nums.length == 0){
            return -1;
        }
        // write code here
        int left = 0, right = nums.length-1;

        while(left <= right){
            int middle = left + ((right - left) >> 1);
            if(nums[middle] > target){
                right = middle;
            } else if(nums[middle] < target){
                left = middle;
            } else {
                return middle;
            }
        }
        return -1;
    }
    public ListNode(int val){
        this.val = val;
    }
    public ListNode sortInList (ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        return sort(head, findTail(head));
    }

    private ListNode findTail(ListNode head) {
        ListNode tail = null;
        while (head != null){
            tail = head;
            head = head.next;
        }
        return tail;
    }

    private ListNode sort(ListNode head, ListNode tail){
        if (head.val == tail.val){
            return head;
        }
        ListNode pre = null;
        ListNode middle = findMiddle(head, tail, pre);
        ListNode node1 = sort(head, pre);
        ListNode node2 = sort(middle, tail);
        ListNode ans = merge(node1, node2);
        return ans;
    }

    private ListNode merge(ListNode node1, ListNode node2) {
        ListNode newHed = null, next = null;
        while (true){
            // 都不为null
            if (node1 != null && node2 != null){
                next = node1.val < node2.val ? node1 : node2;
            }
            // node1 == null, node2 != null
            // node1 != null, node2 == null

        }
    }


    private ListNode findMiddle(ListNode head, ListNode tail, ListNode pre) {
        ListNode fast = head;
        ListNode slow = head;
        pre = head;
        while (fast != null){
            fast = fast.next;
            if (fast == null){
                break;
            }
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = null; // 断开链表
        return slow;
    }


    private ListNode generate(){
        int[] arr = {-869091,-860791,-860466,-860166,-767407,-757897,-748249,-693458,-682870,-608888,-484068,-426572,-406609,-362441,-348926,-227942,-170790,-157789,-132713,-100482,-39078,80573,94840,114739,131739,347325,359115,401563,428207,463504,483610,496406,559374,646432,720732,724427,749542,817675,819197,886101,935294};
        ListNode ans = null,current = null;
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            if (ans == null){
                current = ans = new ListNode(arr[i]);
            }else {
                current.next = new ListNode(arr[i]);
                current = current.next;
            }
        }
        return ans;
    }
}

class TComparator implements Comparator<ListNode>{

    @Override
    public int compare(ListNode o1, ListNode o2) {
        return 0;
    }
}

class TTComparator implements Comparator<ListNode> {

    @Override
    public int compare(ListNode o1, ListNode o2) {
        return o1.val - o2.val;
    }
}