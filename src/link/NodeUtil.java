package link;

import java.util.ArrayList;

public class NodeUtil {
    public static void main(String[] args) {
//        Node node = generateIntegerNode(5, 10);
//        printLink(node);
//        printLink(reverseLinkedList(node));

        Node node = generateSortedIntegerNode(10);
        printLink(node);
    }

    /**
     * 给出一个节点N，左节点left，有节点right， left < right,反转 left -> right
     */

    /**
     * 生成一个顺序链表
     *
     * @param size 链表大小
     * @param range 元素范围[0,range]
     */
    public static Node generateSortedIntegerNode(Integer range) {
        Node head = null;
        Node currentHead = null;
        for (int i = 0; i <= range; i++) {
            Node node = new Node();
            node.value = i;
            if (i == 0) {
                currentHead = node;
                head = node;
            } else {
                // 此处从第二个节点开始
                currentHead.next = node;
                currentHead = currentHead.next;
            }
        }
        return head;
    }

    /**
     * 生成一个整数链表
     *
     * @param size 链表大小
     * @param range 元素范围（-range,range）
     */
    public static Node generateIntegerNode(int size, Integer range) {
        Node head = null;
        Node currentHead = null;
        for (int i = 0; i < size; i++) {
            Node node = new Node();
            node.value = (int)(Math.random() * range) - (int)(Math.random() * range);
            if (i == 0) {
                currentHead = node;
                head = node;
            } else {
               // 此处从第二个节点开始
                currentHead.next = node;
                currentHead = currentHead.next;
            }
        }
        return head;
    }

    /**
     * 測試鏈表反轉
     */
    public static void testReverseLink(int testCount){
        for (int i = 0; i < testCount; i++) {
            Node node = generateIntegerNode(10, 50);
            Node node2 = reverseSimple(node);
            Node node1 = reverseLink(node);
            if (node1.equals(node2)) {
                System.out.println("Nice");
            } else {
                System.out.println("Fuck wrong!");
            }
        }
    }

    /**
     * gpt給的算法
     * @param node
     * @return
     */
    public static Node reverseLinkedList(Node node) {
        Node prev = null;
        Node current = node;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    /**
     * gpt給的，會修改入參
     */
    public static Node reverseLinkFromChatGpt(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node reversedHead = reverseLinkFromChatGpt(node.next);
        node.next.next = node;
        node.next = null;

        return reversedHead;
    }

    public static Node reverseSimple(Node head){
        ArrayList<Object> nodeList = new ArrayList<>();
        while (head != null) {
            nodeList.add(head.value);
            head = head.next;
        }
        int size = nodeList.size();

        Node newHead = null;
        Node currentHead = null;
        for (int i = size - 1; i >= 0; i--) {
            if (i == size - 1) {
                newHead = new Node();
                newHead.value = nodeList.get(i);
                newHead.next = new Node();
                currentHead = newHead.next;
            } else {
                currentHead.value = nodeList.get(i);
                if (i != 0) {
                    currentHead.next = new Node();
                    currentHead = currentHead.next;
                }
            }
        }
        return newHead;
    }
    /**
     * 反转链表
     * 注意：
     */
    public static Node reverseLink(Node head) {
        // 前一个节点
        Node before = null;
        Node oldBefore = null;
        // 当前节点
        Node currentHead = head;
        // 新的头节点
        Node newHead = null;
        while (currentHead != null) {
            if (currentHead.next == null) {
                newHead = currentHead;
                newHead.next = before;
                break;
            }
            // 头结点特殊处理，因为头结点的next要指向null
            if (currentHead == head) {
                before = currentHead;
                currentHead = currentHead.next;
                before.next = null;
            } else {
                oldBefore = before;
                before = currentHead;
                currentHead = currentHead.next;
                before.next = oldBefore;
            }
        }
        return newHead;
    }

    // 打印链表
    public static void printLink(Node head) {
      while (head != null) {
          System.out.print(head.value + " ");
          head = head.next;
      }

      System.out.println();
    }


}
