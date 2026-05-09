package link;

import newcode.ListNode;

public class Main {
    public static void main(String[] args) {
        ListNode listNode = new SortInList().sortInList(ListNode.builderFromArr(new int[]{1,2,2,4,5}));
        ListNode.print(listNode);
    }
}
